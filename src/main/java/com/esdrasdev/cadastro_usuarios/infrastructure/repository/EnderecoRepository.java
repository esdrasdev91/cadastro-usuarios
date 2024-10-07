package com.esdrasdev.cadastro_usuarios.infrastructure.repository;

import com.esdrasdev.cadastro_usuarios.infrastructure.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
}
