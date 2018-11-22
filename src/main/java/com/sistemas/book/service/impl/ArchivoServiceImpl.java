package com.sistemas.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemas.book.dao.IArchivoDAO;
import com.sistemas.book.model.Archivo;
import com.sistemas.book.service.IArchivoService;

@Service
public class ArchivoServiceImpl  implements IArchivoService{
	
	@Autowired
	private IArchivoDAO dao;

	@Override
	public int guardar(Archivo archivo) {
		Archivo ar = dao.save(archivo);
		return (ar.getIdArchivo() > 0) ? 1: 0;
	}

	@Override
	public byte[] leerArchivo(Integer idArchivo) {
		return dao.findOne(idArchivo).getValue();
	}

}
