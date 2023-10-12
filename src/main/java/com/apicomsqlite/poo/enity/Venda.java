package com.apicomsqlite.poo.enity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Venda {

    @Id
    private int id;

    private int idCliente;

    private int idProduto;

    private int quantidade;

    private double totalVenda;

    private double valorDesconto;

    public Venda() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", idCliente='" + idCliente + '\'' +
                ", idProduto='" + idProduto + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", valorDesconto='" + valorDesconto + '\'' +
                ", totalVenda='" + totalVenda + '\'' +
                '}';
    }
}