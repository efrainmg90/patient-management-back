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

import com.sistemas.book.model.Especialidad;
import com.sistemas.book.service.IEspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService service;
		
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Especialidad>> listar(){
		List<Especialidad> especialidad = new ArrayList<>();
		especialidad = service.listar();
		return new ResponseEntity<List<Especialidad>>(especialidad, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
		Especialidad especialidad = service.listarId(id);
		if (especialidad == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(especialidad, HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registrar(@Valid @RequestBody Especialidad Especialidad){
		Especialidad esp = new Especialidad();
		esp = service.registrar(Especialidad);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(esp.getIdEspecialidad()).toUri();
		return ResponseEntity.created(location).body(esp);		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@Valid @RequestBody Especialidad especialidad) {		
		service.modificar(especialidad);
		return new ResponseEntity<Object>(especialidad, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable Integer id) {
		Especialidad esp = service.listarId(id);
		if (esp == null) {
			return new ResponseEntity<Object>("Not found id: "+id,HttpStatus.NOT_FOUND);
		} else {
			service.eliminar(id);
			return new ResponseEntity<Object>("{\"message\": \"Delete succefully with id: "+id+"\"}",HttpStatus.OK);
		}
	}
}
