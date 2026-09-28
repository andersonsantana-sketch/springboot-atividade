package com.example.atividade;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import com.example.atividade.models.Evento;
import com.example.atividade.repositories.ConvidadoRepository;
import com.example.atividade.repositories.EventoRepository;

@Controller
public class EventosController {

    private final EventoRepository eventoRepository;

    private final ConvidadoRepository convidadoRepository;

    public EventosController(EventoRepository eventoRepository,
            ConvidadoRepository convidadoRepository) {

        this.eventoRepository = eventoRepository;
        this.convidadoRepository = convidadoRepository;
    }

    @GetMapping("/eventos/novo")
    public String form(Model model) {

        model.addAttribute("evento", new Evento());

        return "eventos/formEvento";
    }

    @PostMapping("/eventos/salvar")
    public String salvar(@Valid Evento evento, BindingResult result) {

        if (result.hasErrors()) {
            return "eventos/formEvento";
        }

        eventoRepository.save(evento);

        return "redirect:/eventos";
    }

    @GetMapping("/eventos")
    public String listar(Model model) {

        model.addAttribute("eventos", eventoRepository.findAll());

        return "eventos/lista";
    }

    @GetMapping("/eventos/{id}")
    public String detalhar(@PathVariable Long id, Model model) {

        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isEmpty()) {
            return "redirect:/eventos";
        }

        Evento eventoEncontrado = evento.get();

        model.addAttribute("evento", eventoEncontrado);

        model.addAttribute(
                "convidados",
                convidadoRepository.findByEvento(eventoEncontrado)
        );

        return "eventos/detalhes";
    }

    @GetMapping("/eventos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Optional<Evento> evento = eventoRepository.findById(id);

        if (evento.isEmpty()) {
            return "redirect:/eventos";
        }

        model.addAttribute("evento", evento.get());

        return "eventos/formEvento";
    }

    @GetMapping("/eventos/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        eventoRepository.deleteById(id);

        return "redirect:/eventos";
    }
}