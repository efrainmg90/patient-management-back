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

import com.sistemas.book.model.Examen;
import com.sistemas.book.service.IExamenService;

@RestController
@RequestMapping("/examenes")
public class ExamenController {

	@Autowired
	private IExamenService service;
		
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Examen>> listar(){
		List<Examen> exaicos = new ArrayList<>();
		exaicos = service.listar();
		return new ResponseEntity<List<Examen>>(exaicos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
		Examen examen = service.listarId(id);
		if (examen == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(examen,HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Examen examen){
		Examen exa = new Examen();
		exa = service.registrar(examen);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exa.getIdExamen()).toUri();
		return ResponseEntity.created(location).body(exa);		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Examen examen) {		
		service.modificar(examen);
		return new ResponseEntity<Object>(examen,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		Examen exa = service.listarId(id);
		if (exa == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		} else {
			service.eliminar(id);
			return new ResponseEntity<Object>("Delete succesfully with id: "+id,HttpStatus.OK);
		}
	}
}
