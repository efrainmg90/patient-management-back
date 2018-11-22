package com.sistemas.book.service;

import java.util.List;

import com.sistemas.book.model.ConsultaExamen;

public interface IConsultaExamenService extends ICRUD<ConsultaExamen>{

	List<ConsultaExamen> listarExamnesPorConsulta(Integer idConsulta);
}
