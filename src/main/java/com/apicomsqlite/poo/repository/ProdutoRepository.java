package com.apicomsqlite.poo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apicomsqlite.poo.enity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    public boolean existsByNomeProduto(String nome);

    public List<Produto> findByNomeProduto(String nome);

    @Query("select max(s.id) from Produto s")
    public Integer findMaxId();
}