package com.sistemas.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemas.book.model.Paciente;

public interface IPacienteService extends ICRUD<Paciente>{
	Page<Paciente> listarPageable(Pageable pageable);
}
