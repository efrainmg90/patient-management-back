package com.sistemas.book.service;

import java.util.List;

import com.sistemas.book.dto.ConsultaListaExamenDTO;
import com.sistemas.book.dto.ConsultaResumenDTO;
import com.sistemas.book.dto.FiltroConsultaDTO;
import com.sistemas.book.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta>{
	Consulta registrar(ConsultaListaExamenDTO consultaDTO);

	List<Consulta> buscar(FiltroConsultaDTO filtro);
	
	List<Consulta> buscarFecha(FiltroConsultaDTO filtro);
	
	List<ConsultaResumenDTO> listarResumen();
	
	byte[] generarReporte();
}
