package com.ingressou.Ingressou.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbingresso")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDINGRESSO")
    private Integer idIngresso;

    @ManyToOne
    @JoinColumn(name = "CDUSUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "CDEVENTO", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "CDTIPOINGRESSO", nullable = false)
    private TipoIngresso tipoIngresso;

    @Column(name = "DTHRCOMPRA")
    private LocalDateTime dataHoraCompra;

    @Column(name = "QTDCOMPRA")
    private int quantidadeCompra;

    @Column(name = "VLTOTALCOMPRA")
    private BigDecimal valorTotalCompra;

    // Construtor padrão
    public Ingresso() {}

    // Construtor com parâmetros

    public Ingresso(Integer idIngresso, Usuario usuario, Evento evento, TipoIngresso tipoIngresso, LocalDateTime dataHoraCompra, int quantidadeCompra, BigDecimal valorTotalCompra) {
        this.idIngresso = idIngresso;
        this.usuario = usuario;
        this.evento = evento;
        this.tipoIngresso = tipoIngresso;
        this.dataHoraCompra = dataHoraCompra;
        this.quantidadeCompra = quantidadeCompra;
        this.valorTotalCompra = valorTotalCompra;
    }

    // Getters e Setters
    public Integer getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(Integer idIngresso) {
        this.idIngresso = idIngresso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public TipoIngresso getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(TipoIngresso tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public LocalDateTime getDataHoraCompra() {
        return dataHoraCompra;
    }

    public void setDataHoraCompra(LocalDateTime dataHoraCompra) {
        this.dataHoraCompra = dataHoraCompra;
    }

    public int getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public void setQuantidadeCompra(int quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }

    public BigDecimal getValorTotalCompra() {
        return valorTotalCompra;
    }

    public void setValorTotalCompra(BigDecimal valorTotalCompra) {
        this.valorTotalCompra = valorTotalCompra;
    }

    // Método para obter o preço do ingresso (referente ao preço do tipo de ingresso)
    public BigDecimal getPreco() {
        return this.tipoIngresso != null ? this.tipoIngresso.getPrecoBase() : BigDecimal.ZERO;
    }

    // Método para calcular o valor total do ingresso baseado na quantidade e tipo
    public BigDecimal multiplicarTipo() {
        return getPreco().multiply(BigDecimal.valueOf(this.quantidadeCompra));
    }
}
