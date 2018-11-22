package com.sistemas.book.service;

import com.sistemas.book.model.Archivo;

public interface IArchivoService {

	int guardar(Archivo archivo);
	
	byte[] leerArchivo(Integer idArchivo);
}
