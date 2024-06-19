package es.laspalmeras.padel.business.service.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jornada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	String nombre;

	private Date fecha;

	private Campeonato campeonato;

	// Getters y setters

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public Date getFecha() {
		return fecha;
	}

	public Long getId() {
		return id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setId(Long id) {
		this.id = id;
	}
}