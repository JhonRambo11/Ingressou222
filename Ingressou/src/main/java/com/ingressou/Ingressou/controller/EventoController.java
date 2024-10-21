package com.ingressou.Ingressou.controller;

import com.ingressou.Ingressou.model.Evento;
import com.ingressou.Ingressou.service.EventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")  // Mapeamento base
public class EventoController {
    private static final Logger logger = LoggerFactory.getLogger(EventoController.class);
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping("/criar")  // Mapeamento para /api/eventos/criar
    public Evento criarEvento(@RequestBody Evento evento) {
        logger.info("Método criarEvento chamado com evento: {}", evento);
        return eventoService.criarEvento(evento);
    }

    @PutMapping("/atualizar/{id}")  // Mapeamento para /api/eventos/atualizar/{id}
    public Evento atualizarEvento(@PathVariable Integer id, @RequestBody Evento evento) {
        logger.info("Método atualizarEvento chamado para o ID: {}", id);
        Evento eventoAtualizado = eventoService.atualizarEvento(id, evento);

        if (eventoAtualizado != null) {
            logger.info("Evento atualizado com sucesso: {}", eventoAtualizado);
            return eventoAtualizado;
        } else {
            logger.warn("Evento com ID: {} não encontrado para atualização.", id);
            return null; // Ou você pode lançar uma exceção específica
        }
    }

    @DeleteMapping("/deletar/{id}")  // Mapeamento para /api/eventos/deletar/{id}
    public void deletarEvento(@PathVariable Integer id) {
        logger.info("Método deletarEvento chamado para o ID: {}", id);
        eventoService.deletarEvento(id);
        logger.info("Evento com ID: {} deletado com sucesso.", id);
    }

    @GetMapping("/exemplo")  // Mapeamento para /api/eventos/exemplo
    public String exemplo() {
        logger.info("Método exemplo chamado!");
        return "Olá, mundo!";
    }

    //Listar todos: /api/eventos/listar
    //Filtrar por nome: /api/eventos/listar?nome=Festa
    //Filtrar por local: /api/eventos/listar?localEvento=Arena
    //Filtrar por data: /api/eventos/listar?data=2024-10-15
    //Combinar filtros: /api/eventos/listar?nome=Festa&localEvento=Arena&data=2024-10-15
    @GetMapping("/listar")  // Mapeamento para /api/eventos/listar
    public List<Evento> listarEventosFiltrados(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String localEvento,
            @RequestParam(required = false) String data) {

        logger.info("Método listarEventosFiltrados chamado com nome={}, localEvento={}, data={}", nome, localEvento, data);

        return eventoService.listarEventosFiltrados(nome, localEvento, data);
    }

    @GetMapping("/disponibilidade/{id}")  // Mapeamento para /api/eventos/disponibilidade/{id}
    public int verificarDisponibilidade(@PathVariable Integer id) {
        logger.info("Método verificarDisponibilidade chamado para o ID: {}", id);
        return eventoService.calcularDisponibilidade(id);
    }

    @GetMapping("/listar/{id}")  // Mapeamento para /api/eventos/listar/{id}
    public String listarEvento(@PathVariable Integer id) {
        logger.info("Método listarEvento chamado para o ID: {}", id);
        return eventoService.listar(id);
    }

    @GetMapping("/selecionar/{id}")  // Mapeamento para /api/eventos/selecionar/{id}
    public Evento selecionarEvento(@PathVariable Integer id) {
        logger.info("Método selecionarEvento chamado para o ID: {}", id);
        return eventoService.selecionarEvento(id);
    }
}
