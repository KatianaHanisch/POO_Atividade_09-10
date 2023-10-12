package com.apicomsqlite.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apicomsqlite.poo.enity.Estoque;
import com.apicomsqlite.poo.repository.EstoqueRepository;
import jakarta.transaction.Transactional;

@Service
public class EstoqueService {

    @Autowired(required = false)
    private EstoqueRepository estoqueRepository;

    @Transactional
    public String createEstoque(Estoque estoque) {
        try {
            if (!estoqueRepository.existsByIdProduto(estoque.getIdProduto())) {
                estoque.setId(null == estoqueRepository.findMaxId() ? 1 : estoqueRepository.findMaxId() + 1);
                estoqueRepository.save(estoque);
                return "quantidade cadastrado com sucesso.";
            } else {
                return "quantidade já existe no banco.";
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Estoque> readEstoque() {
        return estoqueRepository.findAll();
    }

    @Transactional
    public String updateEstoque(Estoque estoque) {
        if (estoqueRepository.existsByIdProduto(estoque.getIdProduto())) {
            try {
                List<Estoque> estoques = estoqueRepository.findByIdProduto(estoque.getIdProduto());
                estoques.stream().forEach(s -> {
                    Estoque estoqueToBeUpdate = estoqueRepository.findById(s.getId()).get();
                    estoqueToBeUpdate.setQuantidadeProduto(estoque.getQuantidadeProduto());
                    estoqueRepository.save(estoqueToBeUpdate);
                });
                return "estoque atualizado.";
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "estoque não existe no banco.";
        }
    }

    @Transactional
    public String deleteEstoque(Estoque estoque) {
        if (estoqueRepository.existsByIdProduto(estoque.getIdProduto())) {
            try {
                List<Estoque> estoques = estoqueRepository.findByIdProduto(estoque.getIdProduto());
                estoques.stream().forEach(s -> {
                    estoqueRepository.delete(s);
                });
                return "estoque deletado.";
            } catch (Exception e) {
                throw e;
            }

        } else {
            return "estoque n\u00E3o existe no banco.";
        }
    }
}
