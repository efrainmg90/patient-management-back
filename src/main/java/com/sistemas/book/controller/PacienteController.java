package com.sistemas.book.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.sistemas.book.model.Paciente;
import com.sistemas.book.service.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private IPacienteService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Paciente>> listar(){
		List<Paciente> pacientes = new ArrayList<>();
		pacientes = service.listar();
		return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
	}
	
	@GetMapping(value="/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Paciente>> listarPageable(Pageable pageable){
		Page<Paciente> pacientes;
		pacientes = service.listarPageable(pageable);
		return new ResponseEntity<Page<Paciente>>(pacientes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
		Paciente pac = service.listarId(id);
		if (pac == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(pac,HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@Valid @RequestBody Paciente paciente){
		Paciente pac = new Paciente();
		pac = service.registrar(paciente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdPaciente()).toUri();
		return ResponseEntity.created(location).body(pac);		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Paciente paciente) {		
		service.modificar(paciente);
		return new ResponseEntity<Object>(paciente, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> eliminar(@PathVariable Integer id) {
		Paciente pac = service.listarId(id);
		if (pac == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		} else {
			service.eliminar(id);
			return new ResponseEntity<Object>("Delete succesfully with id: "+id,HttpStatus.OK);
		}
	}
}
