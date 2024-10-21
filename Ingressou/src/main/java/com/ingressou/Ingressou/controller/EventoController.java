package com.ingressou.Ingressou.controller;

import com.ingressou.Ingressou.model.Evento;
import com.ingressou.Ingressou.service.EventoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")  // Mapeamento base
public class EventoController {
    private static final Logger logger = LoggerFactory.getLogger(EventoController.class);
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/exemplo")  // Mapeamento para /api/eventos/exemplo
    public String exemplo() {
        logger.info("Método exemplo chamado!");
        return "Olá, mundo!";
    }

    @GetMapping("/listar")  // Mapeamento para /api/eventos/listar
    public List<Evento> listarTodos() {
        logger.info("Método listarTodos chamado!");
        return eventoService.listarTodos(); // Certifique-se de que o serviço está implementado
    }
}
