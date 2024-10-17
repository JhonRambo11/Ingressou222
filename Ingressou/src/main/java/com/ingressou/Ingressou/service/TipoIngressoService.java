package com.ingressou.Ingressou.service;

import com.ingressou.Ingressou.model.Ingresso;
import com.ingressou.Ingressou.model.TipoIngresso;
import com.ingressou.Ingressou.repository.TipoIngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TipoIngressoService {

    @Autowired
    private TipoIngressoRepository tipoIngressoRepository;

    // Salvar ou atualizar um TipoIngresso
    public TipoIngresso salvarTipoIngresso(TipoIngresso tipoIngresso) {
        return tipoIngressoRepository.save(tipoIngresso);
    }

    // Buscar TipoIngresso por ID
    public Optional<TipoIngresso> buscarPorId(Integer id) {
        return tipoIngressoRepository.findById(id);
    }

    // Multiplicar preço base do tipo de ingresso por um fator
    public BigDecimal multiplicarPrecoBase(Integer id, double fator) {
        Optional<TipoIngresso> tipoIngresso = tipoIngressoRepository.findById(id);
        if (tipoIngresso.isPresent()) {
            return tipoIngresso.get().getPrecoBase().multiply(BigDecimal.valueOf(fator));
        } else {
            throw new RuntimeException("TipoIngresso não encontrado");
        }
    }

    // Selecionar um ingresso específico pelo índice
    public Ingresso selecionarIngresso(Integer idTipoIngresso, int index) {
        Optional<TipoIngresso> tipoIngresso = tipoIngressoRepository.findById(idTipoIngresso);
        if (tipoIngresso.isPresent()) {
            List<Ingresso> ingressos = tipoIngresso.get().getIngressos();
            if (index >= 0 && index < ingressos.size()) {
                return ingressos.get(index);
            } else {
                throw new IndexOutOfBoundsException("Índice do ingresso fora dos limites.");
            }
        } else {
            throw new RuntimeException("TipoIngresso não encontrado");
        }
    }
}
