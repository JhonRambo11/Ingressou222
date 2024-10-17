package com.ingressou.Ingressou.controller;

import com.ingressou.Ingressou.model.Ingresso;
import com.ingressou.Ingressou.model.TipoIngresso;
import com.ingressou.Ingressou.service.TipoIngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-ingresso")
public class TipoIngressoController {

    @Autowired
    private TipoIngressoService tipoIngressoService;

    // Endpoint para salvar ou atualizar TipoIngresso
    @PostMapping
    public ResponseEntity<TipoIngresso> salvarTipoIngresso(@RequestBody TipoIngresso tipoIngresso) {
        TipoIngresso salvo = tipoIngressoService.salvarTipoIngresso(tipoIngresso);
        return ResponseEntity.ok(salvo);
    }

    // Endpoint para buscar TipoIngresso por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoIngresso> buscarPorId(@PathVariable Integer id) {
        Optional<TipoIngresso> tipoIngresso = tipoIngressoService.buscarPorId(id);
        return tipoIngresso.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para multiplicar o preço base por um fator
    @GetMapping("/{id}/multiplicar")
    public ResponseEntity<BigDecimal> multiplicarPrecoBase(
            @PathVariable Integer id,
            @RequestParam double fator) {
        try {
            BigDecimal resultado = tipoIngressoService.multiplicarPrecoBase(id, fator);
            return ResponseEntity.ok(resultado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para selecionar um ingresso específico pelo índice
    @GetMapping("/{id}/ingresso/{index}")
    public ResponseEntity<Ingresso> selecionarIngresso(
            @PathVariable Integer id,
            @PathVariable int index) {
        try {
            Ingresso ingresso = tipoIngressoService.selecionarIngresso(id, index);
            return ResponseEntity.ok(ingresso);
        } catch (IndexOutOfBoundsException e) {
            // Erro de índice fora dos limites
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Aqui você pode enviar uma mensagem de erro personalizada se desejar
        } catch (RuntimeException e) {
            // Outra exceção de tempo de execução
            return ResponseEntity.notFound().build();
        }
    }

}
