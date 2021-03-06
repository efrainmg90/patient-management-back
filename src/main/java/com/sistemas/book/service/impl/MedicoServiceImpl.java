package com.sistemas.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.book.dao.IMedicoDAO;
import com.sistemas.book.model.Medico;
import com.sistemas.book.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService{

	@Autowired
	private IMedicoDAO dao;
	
	@Override
	public Medico registrar(Medico t) {
		return dao.save(t);
	}

	@Override
	public Medico modificar(Medico t) {		
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Medico listarId(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Medico> listar() {
		return dao.findAll();
	}

}
