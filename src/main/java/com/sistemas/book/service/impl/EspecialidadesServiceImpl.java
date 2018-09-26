package com.sistemas.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.book.dao.IEspecialidadDAO;
import com.sistemas.book.model.Especialidad;
import com.sistemas.book.service.IEspecialidadService;

@Service
public class EspecialidadesServiceImpl implements IEspecialidadService{

	@Autowired
	private IEspecialidadDAO dao;
	
	@Override
	public Especialidad registrar(Especialidad t) {
		return dao.save(t);
	}

	@Override
	public Especialidad modificar(Especialidad t) {		
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Especialidad listarId(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Especialidad> listar() {
		return dao.findAll();
	}
}
