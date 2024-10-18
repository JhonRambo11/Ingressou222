package com.ingressou.Ingressou.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbusuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDUSUARIO")
    private Integer idUsuario;

    @Column(name = "NMUSUARIO", nullable = false)
    private String nomeUsuario;

    @Column(name = "DSCIDADE", nullable = false)
    private String cidade;

    @Column(name = "DSESTADO", nullable = false)
    private String estado;

    @Column(name = "NUTELEFONE", nullable = false)
    private String telefone;

    @Column(name = "NUIDADE")
    private Integer idade;

    @Column(name = "DSEMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "DSSENHA", columnDefinition = "CHAR(64)")
    private char[] senha;

    @Column(name = "DOCUMENTO", nullable = false, unique = true)
    private String documento;

    // Construtor padrão
    public Usuario() {}

    // Construtor com parâmetros
    public Usuario(String nomeUsuario, String cidade, String estado, String telefone, Integer idade, String email, char[] senha, String documento) {
        this.nomeUsuario = nomeUsuario;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.documento = documento;
    }

    // Getters e Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getSenha() {
        return senha;
    }

    public void setSenha(char[] senha) {
        this.senha = senha;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", telefone='" + telefone + '\'' +
                ", idade=" + idade +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", documento='" + documento + '\'' +
                '}';
    }
}
