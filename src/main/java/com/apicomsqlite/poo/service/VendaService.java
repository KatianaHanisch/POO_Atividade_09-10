package com.apicomsqlite.poo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apicomsqlite.poo.enity.Estoque;
import com.apicomsqlite.poo.enity.Venda;
import com.apicomsqlite.poo.enity.VendaDTO;
import com.apicomsqlite.poo.repository.EstoqueRepository;
import com.apicomsqlite.poo.repository.VendaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class VendaService {

    @Autowired(required = false)
    private VendaRepository vendaRepository;
    @Autowired(required = false)
    private EstoqueRepository estoqueRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    // public String createVenda(Venda venda) {
    // try {
    // if (!vendaRepository.existsById(venda.getId())) {
    // venda.setId(null == vendaRepository.findMaxId() ? 1 :
    // vendaRepository.findMaxId() + 1);
    // vendaRepository.save(venda);
    // return "venda cadastrado com sucesso.";
    // } else {
    // return "venda já existe no banco.";
    // }
    // } catch (Exception e) {
    // throw e;
    // }
    // }

    public String createVenda(Venda venda) {
        try {
            if (!vendaRepository.existsById(venda.getId())) {
                venda.setId(null == vendaRepository.findMaxId() ? 1 : vendaRepository.findMaxId() + 1);
                vendaRepository.save(venda);

                atualizarEstoque(venda);

                return "Venda cadastrada com sucesso.";
            } else {
                return "Venda já existe no banco.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void atualizarEstoque(Venda venda) {
        List<Estoque> estoqueList = estoqueRepository.findByIdProduto(venda.getIdProduto());

        if (!estoqueList.isEmpty()) {
            Estoque estoque = estoqueList.get(0);

            int quantidadeAtual = estoque.getQuantidadeProduto();
            int quantidadeVendida = venda.getQuantidade();

            if (quantidadeAtual >= quantidadeVendida) {
                int novaQuantidade = quantidadeAtual - quantidadeVendida;
                estoque.setQuantidadeProduto(novaQuantidade);
                estoqueRepository.save(estoque);
            } else {
                throw new RuntimeException("Quantidade insuficiente no estoque.");
            }
        } else {
            throw new RuntimeException("Produto não encontrado no estoque.");
        }
    }

    public List<VendaDTO> readVenda() {
        String jpqlQuery = "SELECT c.nome AS nomeCliente, v.idProduto, p.nomeProduto AS nomeProduto, v.quantidade, p.valorProduto, v.valorDesconto "
                +
                "FROM Venda v " +
                "JOIN Cliente c ON v.idCliente = c.id " +
                "JOIN Produto p ON v.idProduto = p.id";

        TypedQuery<Object[]> query = entityManager.createQuery(jpqlQuery, Object[].class);

        List<VendaDTO> resultados = query.getResultList().stream()
                .map(result -> {
                    String nomeCliente = (String) result[0];
                    String nomeProduto = (String) result[2];
                    Integer quantidade = (Integer) result[3];
                    double valorProduto = (double) result[4];
                    double valorDesconto = (double) result[5];
                    double totalVenda = (valorProduto * quantidade) - valorDesconto;
                    return new VendaDTO(nomeCliente, nomeProduto, quantidade, totalVenda, valorDesconto);
                })
                .collect(Collectors.toList());

        return resultados;
    }

    @Transactional
    public String updateVenda(Venda venda) {
        if (vendaRepository.existsById(venda.getId())) {
            try {
                List<Venda> vendas = vendaRepository.findById(venda.getId());
                for (Venda vendaToBeUpdate : vendas) {
                    vendaToBeUpdate.setIdCliente(venda.getIdCliente());
                    vendaToBeUpdate.setIdProduto(venda.getIdProduto());
                    vendaToBeUpdate.setQuantidade(venda.getQuantidade());
                    vendaToBeUpdate.setValorDesconto(venda.getValorDesconto());
                    vendaRepository.save(vendaToBeUpdate);
                }
                return "venda atualizada.";
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "venda não existe no banco.";
        }
    }

    @Transactional
    public String deleteVenda(Venda venda) {
        if (vendaRepository.existsById(venda.getId())) {
            try {
                List<Venda> vendas = vendaRepository.findById(venda.getId());
                vendas.stream().forEach(s -> {
                    vendaRepository.delete(s);
                });
                return "venda deletado.";
            } catch (Exception e) {
                throw e;
            }

        } else {
            return "venda n\u00E3o existe no banco.";
        }
    }
}