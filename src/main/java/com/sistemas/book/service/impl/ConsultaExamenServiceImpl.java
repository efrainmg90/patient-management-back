package com.sistemas.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.book.dao.IConsultaExamenDAO;
import com.sistemas.book.model.ConsultaExamen;
import com.sistemas.book.service.IConsultaExamenService;

@Service
public class ConsultaExamenServiceImpl implements IConsultaExamenService{

	@Autowired
	private IConsultaExamenDAO dao;

	@Override
	public ConsultaExamen registrar(ConsultaExamen t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsultaExamen modificar(ConsultaExamen t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConsultaExamen listarId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConsultaExamen> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConsultaExamen> listarExamnesPorConsulta(Integer idConsulta) {
		//return dao.li;
		return dao.listarExamenesPorConsulta(idConsulta);
	}
	
	
	
}
