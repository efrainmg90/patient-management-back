package com.sistemas.book.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemas.book.model.ConsultaExamen;
import com.sistemas.book.service.IConsultaExamenService;

@RestController
@RequestMapping("/consultaexamenes")
public class ConsultaExamenController {

	@Autowired
	private IConsultaExamenService service;
	
	@GetMapping(value="/{idConsulta}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ConsultaExamen>> lista(@PathVariable("idConsulta") Integer idConsulta){
		List<ConsultaExamen> conexamneList = new ArrayList<>();
		conexamneList = service.listarExamnesPorConsulta(idConsulta);
		return new ResponseEntity<List<ConsultaExamen>>(conexamneList,HttpStatus.OK);
	}
}
