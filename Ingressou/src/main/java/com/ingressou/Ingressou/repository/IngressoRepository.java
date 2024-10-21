package com.ingressou.Ingressou.repository;

import com.ingressou.Ingressou.model.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
    // Nenhum método adicional é necessário para findById
}