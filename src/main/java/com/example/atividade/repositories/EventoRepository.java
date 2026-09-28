package com.example.atividade.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.atividade.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}