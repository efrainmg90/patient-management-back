package com.sistemas.book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ConsultaExamen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idConsultaExamen;

	
	@ManyToOne
	@JoinColumn(name = "id_examen", nullable = false)
	private Examen examen;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_consulta", nullable = false)
	private Consulta consulta;
	

	public ConsultaExamen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaExamen(Consulta consulta,Examen examen) {
		super();
		this.examen = examen;
		this.consulta = consulta;
	}

	public int getIdConsultaExamen() {
		return idConsultaExamen;
	}

	public void setIdConsultaExamen(int idConsultaExamen) {
		this.idConsultaExamen = idConsultaExamen;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
	

}
