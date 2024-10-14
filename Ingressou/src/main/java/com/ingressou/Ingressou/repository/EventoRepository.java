package com.ingressou.Ingressou.repository;

import com.ingressou.Ingressou.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> { // Alterado para Integer, caso cdEvento seja int

    // Busca eventos pelo nome
    List<Evento> findByNome(String nome);

    // Busca eventos por data
    List<Evento> findByDataEvento(LocalDate dataEvento);

    // Busca eventos com capacidade maior que um determinado valor
    List<Evento> findByCapacidadeGreaterThan(int capacidade);
}
