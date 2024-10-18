package com.ingressou.Ingressou.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbpedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDPEDIDO")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "CDUSUARIO", nullable = false)
    private Usuario usuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "DTPEDIDO", nullable = false)
    private Date dataPedido;

    @Column(name = "DSSTATUS", nullable = false)
    private String status;

    @Column(name = "VLTOTALPEDIDO", nullable = false)
    private BigDecimal valorTotalPedido; // Alterado para BigDecimal

    // Getters e Setters

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", usuario=" + usuario +
                ", dataPedido=" + dataPedido +
                ", status='" + status + '\'' +
                ", valorTotalPedido=" + valorTotalPedido +
                '}';
    }
}
