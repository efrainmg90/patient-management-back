package com.sistemas.book.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistemas.book.dto.ConsultaListaExamenDTO;
import com.sistemas.book.model.Consulta;
import com.sistemas.book.service.IConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService service;
		
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Consulta>> listar(){
		List<Consulta> consecialidad = new ArrayList<>();
		consecialidad = service.listar();
		return new ResponseEntity<List<Consulta>>(consecialidad, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
		Consulta consulta = service.listarId(id);
		if (consulta == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(consulta, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@Valid @RequestBody ConsultaListaExamenDTO consultaDTO){
		Consulta cons = new Consulta();
		cons = service.registrar(consultaDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cons.getIdConsulta()).toUri();
		return ResponseEntity.created(location).body(cons);		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@Valid @RequestBody Consulta consulta) {		
		service.modificar(consulta);
		return new ResponseEntity<Consulta>(consulta,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		Consulta cons = service.listarId(id);
		if (cons == null) {
			return new ResponseEntity<String>("Not found id: "+id,HttpStatus.NOT_FOUND);
		} else {
			service.eliminar(id);
			return new ResponseEntity<String>("Delete succesfully with id: "+id,HttpStatus.OK);
		}
	}
}
