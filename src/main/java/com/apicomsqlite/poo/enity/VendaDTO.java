package com.apicomsqlite.poo.enity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VendaDTO {
    private String nomeCliente;
    private String nomeProduto;
    private Integer quantidade;
    private double totalVenda;
    private double valorDesconto;

    public VendaDTO() {
        // Construtor padrão vazio
    }

    public VendaDTO(String nomeCliente, String nomeProduto, Integer quantidade, double totalVenda,
            double valorDesconto) {
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.totalVenda = totalVenda;
        this.valorDesconto = valorDesconto;
    }

    // Getters e setters
}
