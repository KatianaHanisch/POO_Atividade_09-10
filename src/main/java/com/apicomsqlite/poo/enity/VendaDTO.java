package com.apicomsqlite.poo.enity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VendaDTO {
    private String nomeCliente;
    private String cpfCliente;
    private String nomeProduto;
    private String unidadeDeMedida;
    private Integer quantidade;
    private double totalVenda;
    private double valorDesconto;
    private double valorProduto;

    public VendaDTO() {
        // Construtor padrão vazio
    }

    public VendaDTO(String nomeCliente, String cpfCliente, String nomeProduto, String unidadeDeMedida,
            Integer quantidade, double totalVenda, double valorDesconto, double valorProduto) {
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.nomeProduto = nomeProduto;
        this.unidadeDeMedida = unidadeDeMedida;
        this.quantidade = quantidade;
        this.totalVenda = totalVenda;
        this.valorDesconto = valorDesconto;
        this.valorProduto = valorProduto;
    }

    // Getters e setters
}
