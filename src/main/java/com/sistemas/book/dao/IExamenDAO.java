package com.sistemas.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas.book.model.Examen;

public interface IExamenDAO extends JpaRepository<Examen, Integer> {

}
