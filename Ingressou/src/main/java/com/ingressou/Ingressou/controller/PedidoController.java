package com.ingressou.Ingressou.controller;

import com.ingressou.Ingressou.model.Pedido;
import com.ingressou.Ingressou.model.Usuario;
import com.ingressou.Ingressou.service.PedidoService;
import com.ingressou.Ingressou.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private UsuarioService usuarioService; // Supondo que você tenha um serviço de usuário

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestParam Integer usuarioId, @RequestParam BigDecimal valorTotal) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId); // Busca o usuário pelo ID

        Pedido pedido = pedidoService.criarPedido(usuario, valorTotal);
        return ResponseEntity.ok(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Long id) {
        pedidoService.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
