package com.apicomsqlite.poo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apicomsqlite.poo.enity.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    public boolean existsByIdProduto(int idProduto);

    public List<Estoque> findByIdProduto(int idProduto);

    @Query("select max(s.id) from Estoque s")
    public Integer findMaxId();
}