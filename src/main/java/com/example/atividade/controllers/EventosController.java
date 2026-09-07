package com.example.atividade.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.atividade.models.Evento;
import com.example.atividade.repositories.EventoRepository;

@Controller
public class EventosController {

    private final EventoRepository eventoRepository;

    public EventosController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
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

        model.addAttribute("evento", evento.get());

        return "eventos/detalhes";
    }
}