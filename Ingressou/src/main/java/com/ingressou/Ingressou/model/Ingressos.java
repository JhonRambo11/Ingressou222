package com.ingressou.Ingressou.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tbingresso")
public class Ingressos {

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

    @Column(name = "DTHRCOMPRA", nullable = false)
    private java.sql.Timestamp dataCompra;

    @Column(name = "QTDCOMPRA", nullable = false)
    private Integer quantidadeCompra;

    @Column(name = "VLTOTALCOMPRA", nullable = false)
    private BigDecimal valorTotalCompra;

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

    public java.sql.Timestamp getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(java.sql.Timestamp dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public void setQuantidadeCompra(Integer quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }

    public BigDecimal getValorTotalCompra() {
        return valorTotalCompra;
    }

    public void setValorTotalCompra(BigDecimal valorTotalCompra) {
        this.valorTotalCompra = valorTotalCompra;
    }
}
