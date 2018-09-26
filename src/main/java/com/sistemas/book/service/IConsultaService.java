package com.sistemas.book.service;

import com.sistemas.book.dto.ConsultaListaExamenDTO;
import com.sistemas.book.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta>{
	Consulta registrar(ConsultaListaExamenDTO consultaDTO);

}
