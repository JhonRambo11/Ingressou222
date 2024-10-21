package com.ingressou.Ingressou.service;

import com.ingressou.Ingressou.model.Evento;
import com.ingressou.Ingressou.repository.EventoRepository;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    @PostConstruct
    public void criarEventosDeTeste() {
        // Verifica se já existem eventos no banco
        if (eventoRepository.count() == 0) { // Apenas cria eventos se a tabela estiver vazia

            // Criando o primeiro evento de teste
            Evento evento1 = new Evento();
            evento1.setNome("Evento Teste 1");
            evento1.setDescricao("Descrição do Evento Teste 1");
            evento1.setDataEvento(LocalDate.now());
            evento1.setLocalEvento("Local 1");
            evento1.setFaixaEtaria(18);
            evento1.setImagemUrl("url_imagem_1");
            evento1.setHoraAbertura(LocalTime.of(18, 0));
            evento1.setHoraInicio(LocalTime.of(20, 0));
            evento1.setCapacidade(100);
            evento1.setQuantidadeIngressos(100);
            eventoRepository.save(evento1);

            // Criando o segundo evento de teste
            Evento evento2 = new Evento();
            evento2.setNome("Evento Teste 2");
            evento2.setDescricao("Descrição do Evento Teste 2");
            evento2.setDataEvento(LocalDate.now().plusDays(1));
            evento2.setLocalEvento("Local 2");
            evento2.setFaixaEtaria(16);
            evento2.setImagemUrl("url_imagem_2");
            evento2.setHoraAbertura(LocalTime.of(19, 0));
            evento2.setHoraInicio(LocalTime.of(21, 0));
            evento2.setCapacidade(200);
            evento2.setQuantidadeIngressos(200);
            eventoRepository.save(evento2);

            System.out.println("Eventos de teste criados com sucesso!");
        } else {
            System.out.println("Eventos já existem no banco de dados. Nenhum evento de teste criado.");
        }
    }
}
