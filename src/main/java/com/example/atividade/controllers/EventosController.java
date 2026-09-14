package com.example.atividade.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.atividade.models.Evento;
import com.example.atividade.repositories.EventoRepository;
import com.example.atividade.repositories.ConvidadoRepository;

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
    public String form() {
        return "eventos/formEvento";
    }

    @PostMapping("/eventos/salvar")
    public String salvar(Evento evento) {

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
}