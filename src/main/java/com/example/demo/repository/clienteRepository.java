package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.cliente;

@Repository
public interface clienteRepository extends JpaRepository<cliente, Long> {
}
