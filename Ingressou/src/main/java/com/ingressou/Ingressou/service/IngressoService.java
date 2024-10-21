package com.ingressou.Ingressou.service;

import com.ingressou.Ingressou.model.Ingresso;
import com.ingressou.Ingressou.repository.IngressoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

public class IngressoService {
    @Service
    public class IngressosService {

        @Autowired
        private IngressoRepository ingressoRepository;

        // Multiplicar o preço do ingresso por um fator
        public BigDecimal multiplicarTipo(Long id) {
            Optional<Ingresso> ingressoOpt = ingressoRepository.findById(id);

            if (ingressoOpt.isPresent()) {
                Ingresso ingresso = ingressoOpt.get();
                BigDecimal precoBase = ingresso.getTipoIngresso().getPrecoBase();
                return precoBase.multiply(BigDecimal.valueOf(ingresso.getQuantidadeCompra()));
            } else {
                throw new EntityNotFoundException("Ingresso não encontrado com o ID: " + id);
            }
        }

        // Selecionar um ingresso específico
        public Ingresso selecionarIngresso(Long id) {
            return ingressoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));
        }
    }
}
