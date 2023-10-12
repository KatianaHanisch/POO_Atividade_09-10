package com.apicomsqlite.poo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apicomsqlite.poo.enity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public boolean existsByNome(String nome);

    public List<Cliente> findByNome(String nome);

    @Query("select max(s.id) from Cliente s")
    public Integer findMaxId();
}