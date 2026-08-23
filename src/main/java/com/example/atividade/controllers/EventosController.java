package com.example.atividade.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.atividade.models.Evento;

@Controller
public class EventosController {

    @RequestMapping("/eventos")
    public String form() {
        return "formEvento";
    }

    @PostMapping("/eventos/salvar")
    public String salvar(Evento evento) {

        System.out.println("Nome: " + evento.getNome());
        System.out.println("Local: " + evento.getLocal());
        System.out.println("Data: " + evento.getData());
        System.out.println("Horário: " + evento.getHorario());

        return "home";
    }
}