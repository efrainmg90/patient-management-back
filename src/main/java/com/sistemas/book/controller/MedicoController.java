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

import com.sistemas.book.model.Medico;
import com.sistemas.book.service.IMedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medico>> listar(){
		List<Medico> medicos = new ArrayList<>();
		medicos = service.listar();
		return new ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
		Medico medico = service.listarId(id);
		if (medico == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(medico,HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Medico Medico){
		Medico med = new Medico();
		med = service.registrar(Medico);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(med.getIdMedico()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Medico Medico) {		
		service.modificar(Medico);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		Medico med = service.listarId(id);
		if (med == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		} else {
			service.eliminar(id);
			return new ResponseEntity<Object>("Delete succesfully with id: "+id,HttpStatus.OK);
		}
	}

}
