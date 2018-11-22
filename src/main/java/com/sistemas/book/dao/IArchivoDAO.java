package com.sistemas.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas.book.model.Archivo;

public interface IArchivoDAO extends JpaRepository<Archivo, Integer>{

}
