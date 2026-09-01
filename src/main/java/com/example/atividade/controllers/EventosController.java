package com.example.atividade.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.atividade.models.Evento;
import com.example.atividade.repositories.EventoRepository;

@Controller
public class EventosController {

    private final EventoRepository eventoRepository;

    public EventosController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @RequestMapping("/eventos")
    public String form() {
        return "formEvento";
    }

    @PostMapping("/eventos/salvar")
    public String salvar(Evento evento) {

        eventoRepository.save(evento);

        return "redirect:/eventos/listar";
    }

    @GetMapping("/eventos/listar")
    public String listar(Model model) {

        model.addAttribute("eventos", eventoRepository.findAll());

        return "index";
    }
}