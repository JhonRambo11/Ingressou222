package com.ingressou.Ingressou.controller;

import com.ingressou.Ingressou.model.Ingresso;
import com.ingressou.Ingressou.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IngressoController {
    @Autowired
    private IngressoRepository ingressoRepository;

    // Multiplicar o preço do ingresso por um fator
    public Double multiplicarTipo(Long id, double fator) {
        Optional<Ingresso> ingresso = ingressoRepository.findById(id);
        if (ingresso.isPresent()) {
            double novoPreco = ingresso.get().getPreco().doubleValue() * fator;
            return novoPreco;
        } else {
            throw new RuntimeException("Ingresso não encontrado");
        }
    }

    // Selecionar um ingresso específico
    public Ingresso selecionarIngresso(Long id) {
        return ingressoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));
    }
}
