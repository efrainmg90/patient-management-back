package com.sistemas.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas.book.model.Paciente;

public interface IPacienteDAO extends JpaRepository<Paciente, Integer>{

}
