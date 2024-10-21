package com.ingressou.Ingressou.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbtipoingresso")
public class TipoIngresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDTIPOINGRESSO")
    private Integer id;

    @Column(name = "VLPRECOBASE")
    private BigDecimal precoBase;

    @Column(name = "NMTIPOINGRESSO") // Adicionando um campo para o nome do tipo de ingresso
    private String nome;

    // Usando @OneToMany para representar a relação com Ingresso
    @OneToMany(mappedBy = "tipoIngresso", cascade = CascadeType.ALL)
    private List<Ingresso> ingressos = new ArrayList<>();


    // Construtor padrão
    public TipoIngresso() {}

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }
    public void addIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
        ingresso.setTipoIngresso(this); // Define o tipo de ingresso no ingresso
    }
}
