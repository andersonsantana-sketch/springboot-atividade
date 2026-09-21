package com.example.atividade.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.atividade.models.Convidado;
import com.example.atividade.models.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidado, Long> {

    List<Convidado> findByEvento(Evento evento);

}