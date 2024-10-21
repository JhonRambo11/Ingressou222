package com.ingressou.Ingressou.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbtipoingresso")
public class TipoIngresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDTIPOINGRESSO")
    private Integer idTipoIngresso;

    @ManyToOne
    @JoinColumn(name = "CDEVENTO", nullable = false)
    private Evento evento;

    @Column(name = "NMTIPOINGRESSO", nullable = false)
    private String nomeTipoIngresso;

    @Column(name = "VLPRECOBASE", nullable = false)
    private BigDecimal precoBase;

    @OneToMany(mappedBy = "tipoIngresso") // Relacionamento com Ingressos
    private List<Ingressos> ingressos;

    // Construtor padrão
    public TipoIngresso() {}

    // Construtor com parâmetros
    public TipoIngresso(String nomeTipoIngresso, BigDecimal precoBase) {
        this.nomeTipoIngresso = nomeTipoIngresso;
        this.precoBase = precoBase;
    }

    // Getters e Setters
    public Integer getIdTipoIngresso() {
        return idTipoIngresso;
    }

    public void setIdTipoIngresso(Integer idTipoIngresso) {
        this.idTipoIngresso = idTipoIngresso;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getNomeTipoIngresso() {
        return nomeTipoIngresso;
    }

    public void setNomeTipoIngresso(String nomeTipoIngresso) {
        this.nomeTipoIngresso = nomeTipoIngresso;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public List<Ingressos> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingressos> ingressos) {
        this.ingressos = ingressos;
    }

    @Override
    public String toString() {
        return "TipoIngresso{" +
                "idTipoIngresso=" + idTipoIngresso +
                ", nomeTipoIngresso='" + nomeTipoIngresso + '\'' +
                ", precoBase=" + precoBase +
                '}';
    }
}
