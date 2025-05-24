package com.checkinone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.checkinone.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
