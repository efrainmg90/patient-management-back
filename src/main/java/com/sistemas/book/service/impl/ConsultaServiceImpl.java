package com.sistemas.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemas.book.dao.IConsultaDAO;
import com.sistemas.book.dao.IConsultaExamenDAO;
import com.sistemas.book.dto.ConsultaListaExamenDTO;
import com.sistemas.book.model.Consulta;
import com.sistemas.book.model.ConsultaExamen;
import com.sistemas.book.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService{


	@Autowired
	private IConsultaDAO dao;
	
	@Autowired
	private IConsultaExamenDAO ceDAO;
	
	@Override
	public Consulta registrar(Consulta t) {
		return dao.save(t);
	}

	@Override
	public Consulta modificar(Consulta t) {		
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Consulta listarId(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Consulta> listar() {
		return dao.findAll();
	}

	@Transactional
	@Override
	public Consulta registrar(ConsultaListaExamenDTO consultaDTO) {
		consultaDTO.getConsulta().getDetalleConsulta().forEach(x -> x.setConsulta(consultaDTO.getConsulta()));
		dao.save(consultaDTO.getConsulta());
		consultaDTO.getLstExamen().forEach(e -> ceDAO.save(new ConsultaExamen(consultaDTO.getConsulta(), e)));
		return consultaDTO.getConsulta();
	}
}
