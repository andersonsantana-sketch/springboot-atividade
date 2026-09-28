package com.example.atividade.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import com.example.atividade.models.Convidado;
import com.example.atividade.repositories.ConvidadoRepository;

@Controller
public class ConvidadosController {

    private final ConvidadoRepository convidadoRepository;

    public ConvidadosController(ConvidadoRepository convidadoRepository) {
        this.convidadoRepository = convidadoRepository;
    }

    @GetMapping("/convidados/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Optional<Convidado> convidado = convidadoRepository.findById(id);

        if (convidado.isEmpty()) {
            return "redirect:/eventos";
        }

        model.addAttribute("convidado", convidado.get());

        return "eventos/formConvidado";
    }

    @PostMapping("/convidados/salvar")
    public String salvar(@Valid Convidado convidado,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "eventos/formConvidado";
        }

        convidadoRepository.save(convidado);

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Convidado salvo com sucesso!"
        );

        return "redirect:/eventos";
    }

    @GetMapping("/convidados/remover/{id}")
    public String remover(@PathVariable Long id,
                          RedirectAttributes redirectAttributes) {

        convidadoRepository.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Convidado removido com sucesso!"
        );

        return "redirect:/eventos";
    }
}