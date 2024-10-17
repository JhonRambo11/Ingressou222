package com.ingressou.Ingressou.controller;

import com.ingressou.Ingressou.model.Usuario;
import com.ingressou.Ingressou.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para Login
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam char[] senha) {
        Usuario usuario = usuarioService.login(email, senha);
        return ResponseEntity.ok(usuario);
    }

    // Endpoint para Criar Conta
    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarConta(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarConta(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    // Endpoint para Editar Dados
    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> editarDados(@PathVariable Integer id, @RequestBody Usuario novosDados) {
        Usuario usuarioAtualizado = usuarioService.editarDados(id, novosDados);
        return ResponseEntity.ok(usuarioAtualizado);
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
