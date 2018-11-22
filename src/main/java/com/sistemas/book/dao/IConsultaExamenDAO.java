package com.sistemas.book.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistemas.book.model.ConsultaExamen;

public interface IConsultaExamenDAO extends JpaRepository<ConsultaExamen, Integer> {

	@Query("from ConsultaExamen ce where ce.consulta.idConsulta = :idConsulta")
	List<ConsultaExamen> listarExamenesPorConsulta(@Param("idConsulta") Integer idConsulta);
}
