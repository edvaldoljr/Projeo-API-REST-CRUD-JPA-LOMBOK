package com.projeto.repository;

import com.projeto.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityPessoaRepository extends JpaRepository<PessoaEntity, Long> {
}