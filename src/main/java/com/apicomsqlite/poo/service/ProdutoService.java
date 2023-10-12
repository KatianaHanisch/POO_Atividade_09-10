package com.apicomsqlite.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apicomsqlite.poo.enity.Produto;
import com.apicomsqlite.poo.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Autowired(required = false)
    private ProdutoRepository produtoRepository;

    @Transactional
    public String createProduto(Produto produto) {
        try {
            if (!produtoRepository.existsByNomeProduto(produto.getNomeProduto())) {
                produto.setId(null == produtoRepository.findMaxId() ? 1 : produtoRepository.findMaxId() + 1);
                produtoRepository.save(produto);
                return "produto cadastrado com sucesso.";
            } else {
                return "produto já existe no banco.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Produto> readProdutos() {
        return produtoRepository.findAll();
    }

    @Transactional
    public String updateProduto(Produto produto) {
        if (produtoRepository.existsByNomeProduto(produto.getNomeProduto())) {
            try {
                List<Produto> produtos = produtoRepository.findByNomeProduto(produto.getNomeProduto());
                produtos.stream().forEach(s -> {
                    Produto produtoToBeUpdate = produtoRepository.findById(s.getId()).get();
                    produtoToBeUpdate.setUnidadeDeMedida(produto.getUnidadeDeMedida());
                    produtoToBeUpdate.setValorProduto(produto.getValorProduto());
                    produtoRepository.save(produtoToBeUpdate);
                });
                return "produto atualizado.";
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "produto não existe no banco.";
        }
    }

    @Transactional
    public String deleteProduto(Produto produto) {
        if (produtoRepository.existsByNomeProduto(produto.getNomeProduto())) {
            try {
                List<Produto> produtos = produtoRepository.findByNomeProduto(produto.getNomeProduto());
                produtos.stream().forEach(s -> {
                    produtoRepository.delete(s);
                });
                return "produto deletado.";
            } catch (Exception e) {
                throw e;
            }

        } else {
            return "produto n\u00E3o existe no banco.";
        }
    }
}