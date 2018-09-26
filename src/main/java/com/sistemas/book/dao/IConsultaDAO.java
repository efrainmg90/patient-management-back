package com.sistemas.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas.book.model.Consulta;


public interface IConsultaDAO extends JpaRepository<Consulta, Integer>{

}