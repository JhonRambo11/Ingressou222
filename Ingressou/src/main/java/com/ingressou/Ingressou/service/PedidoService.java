package com.ingressou.Ingressou.service;

import com.ingressou.Ingressou.model.Pedido;
import com.ingressou.Ingressou.model.Usuario;
import com.ingressou.Ingressou.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Usuario usuario, BigDecimal valorTotal) {
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDataPedido(new Date()); // Data atual
        pedido.setStatus("Criado");
        pedido.setValorTotalPedido(valorTotal);

        return pedidoRepository.save(pedido);
    }

    public void cancelarPedido(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus("Cancelado");
        pedidoRepository.save(pedido);
    }
}
