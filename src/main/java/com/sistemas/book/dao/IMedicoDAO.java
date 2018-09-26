package com.sistemas.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas.book.model.Medico;

public interface IMedicoDAO extends JpaRepository<Medico, Integer>{

}
