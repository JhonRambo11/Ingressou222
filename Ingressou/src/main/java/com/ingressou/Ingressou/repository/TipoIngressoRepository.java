package com.ingressou.Ingressou.repository;

import com.ingressou.Ingressou.model.TipoIngresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIngressoRepository extends JpaRepository<TipoIngresso, Integer> {

}