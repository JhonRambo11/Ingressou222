package com.ingressou.Ingressou.service;

import com.ingressou.Ingressou.model.Usuario;
import com.ingressou.Ingressou.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Método de Login
    public Usuario login(String email, char[] senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent() && passwordEncoder.matches(new String(senha), String.valueOf(usuarioOptional.get().getSenha()))) {
            return usuarioOptional.get();
        } else {
            throw new RuntimeException("Email ou senha inválidos.");
        }
    }

    // Método de Criar Conta
    public Usuario criarConta(Usuario usuario) {
        // Convertendo a senha de char para String e gerando o hash
        String senhaHash = passwordEncoder.encode(new String(usuario.getSenha()));
        // Preenchendo a senha com espaços até atingir 64 caracteres
        usuario.setSenha(fillCharArray(senhaHash.toCharArray(), ' ', 64));
        return usuarioRepository.save(usuario);
    }

    // Método de Editar Dados
    public Usuario editarDados(Integer idUsuario, Usuario novosDados) {
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        // Atualiza os dados conforme os novos dados informados
        usuarioExistente.setNomeUsuario(novosDados.getNomeUsuario());
        usuarioExistente.setCidade(novosDados.getCidade());
        usuarioExistente.setEstado(novosDados.getEstado());
        usuarioExistente.setTelefone(novosDados.getTelefone());
        usuarioExistente.setIdade(novosDados.getIdade());
        usuarioExistente.setEmail(novosDados.getEmail());

        // Se a nova senha for fornecida, faça o hash e atualize
        if (novosDados.getSenha() != null && novosDados.getSenha().length > 0) {
            String senhaHash = passwordEncoder.encode(new String(novosDados.getSenha()));
            usuarioExistente.setSenha(fillCharArray(senhaHash.toCharArray(), ' ', 64));
        }

        usuarioExistente.setDocumento(novosDados.getDocumento());

        return usuarioRepository.save(usuarioExistente);
    }

    private char[] fillCharArray(char[] array, char fillChar, int length) {
        char[] filledArray = new char[length];
        for (int i = 0; i < length; i++) {
            if (i < array.length) {
                filledArray[i] = array[i];
            } else {
                filledArray[i] = fillChar;  // Preenche o restante com o caractere especificado
            }
        }
        return filledArray;
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Integer id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    /*@PostConstruct
    public void init() {
        criarUsuariosParaTeste();
    }
    private void criarUsuariosParaTeste() {
        Usuario usuario1 = new Usuario("João Silva", "São Paulo", "SP", "11987654321", 30, "joao.silva@email.com", "1234567890123456789012345678901234567890123456789012345678901234".toCharArray(), "12345678901");
        Usuario usuario2 = new Usuario("Maria Oliveira", "Rio de Janeiro", "RJ", "21987654321", 25, "maria.oliveira@email.com", "2345678901234567890123456789012345678901234567890123456789012345".toCharArray(), "10987654321");

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
    }*/
}