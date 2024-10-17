package com.ingressou.Ingressou.repository;

import com.ingressou.Ingressou.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    // Busca eventos pelo nome
    List<Evento> findByNome(String nome);

    // Busca eventos por data
    List<Evento> findByDataEvento(LocalDate dataEvento);

    // Busca eventos com capacidade maior que um determinado valor
    List<Evento> findByCapacidadeGreaterThan(int capacidade);

    /*
    exemplo filtragem
    Por Nome:
    http://localhost:8080/api/eventos/listar?nome=Evento%20Teste%201
    Por Local:
    http://localhost:8080/api/eventos/listar?localEvento=Local%201
    Por Data:
    http://localhost:8080/api/eventos/listar?data=2024-10-10
    Combinação de Nome e Local:
    http://localhost:8080/api/eventos/listar?nome=Evento%20Teste%201&localEvento=Local%201&data=2024-10-10
    */

    // Consulta personalizada para buscar eventos com filtros opcionais
    @Query("SELECT e FROM Evento e WHERE " +
            "(:nome IS NULL OR e.nome LIKE %:nome%) AND " +
            "(:localEvento IS NULL OR e.localEvento LIKE %:localEvento%) AND " +
            "(:data IS NULL OR e.dataEvento = :data)")
    List<Evento> listarEventosFiltrados(@Param("nome") String nome,
                                        @Param("localEvento") String localEvento,
                                        @Param("data") LocalDate data);
}
