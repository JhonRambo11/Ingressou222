package com.ingressou.Ingressou.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tbevento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDEVENTO")
    private Integer id;  // Altere de Long para Integer

    @Column(name = "NMEVENTO", nullable = false)
    private String nome;

    @Column(name = "DSDESCRICAO", length = 500)
    private String descricao;

    @Column(name = "DTEVENTO", nullable = false)
    private LocalDate dataEvento;

    @Column(name = "DSLOCALEVENTO", nullable = false)
    private String localEvento;

    @Column(name = "NUFAIXAETARIA")
    private int faixaEtaria;

    @Column(name = "IMEVENTOURL")
    private String imagemUrl;

    @Column(name = "HRABERTURAEVENTO", nullable = false)
    private LocalTime horaAbertura;

    @Column(name = "HRINICIO", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "NUCAPACIDADE", nullable = false)
    private int capacidade;

    @Column(name = "NUQTDINGRESSOS")
    private int quantidadeIngressos;

    @OneToMany(mappedBy = "evento") // Relacionamento com Ingressos (ajuste conforme necessário)
    private List<Ingresso> ingressos;

    // Construtor padrão
    public Evento() {}

    // Construtor com parâmetros
    public Evento(String nome, String descricao, LocalDate dataEvento, String localEvento, int faixaEtaria,
                  String imagemUrl, LocalTime horaAbertura, LocalTime horaInicio, int capacidade, int quantidadeIngressos) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataEvento = dataEvento;
        this.localEvento = localEvento;
        this.faixaEtaria = faixaEtaria;
        this.imagemUrl = imagemUrl;
        this.horaAbertura = horaAbertura;
        this.horaInicio = horaInicio;
        this.capacidade = capacidade;
        this.quantidadeIngressos = quantidadeIngressos;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }

    public int getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(int faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public LocalTime getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(LocalTime horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getQuantidadeIngressos() {
        return quantidadeIngressos;
    }

    public void setQuantidadeIngressos(int quantidadeIngressos) {
        this.quantidadeIngressos = quantidadeIngressos;
    }

    // Método para calcular ingressos disponíveis
    public int calcularIngressosDisponiveis() {
        return capacidade - quantidadeIngressos;
    }

    // Método para listar as informações do evento
    public String listar() {
        return "Nome: " + nome +
                ", Data: " + dataEvento +
                ", Local: " + localEvento +
                ", Disponibilidade: " + calcularIngressosDisponiveis() + " ingressos disponíveis.";
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataEvento=" + dataEvento +
                ", localEvento='" + localEvento + '\'' +
                ", faixaEtaria=" + faixaEtaria +
                ", imagemUrl='" + imagemUrl + '\'' +
                ", horaAbertura=" + horaAbertura +
                ", horaInicio=" + horaInicio +
                ", capacidade=" + capacidade +
                ", quantidadeIngressos=" + quantidadeIngressos +
                '}';
    }
}
