package es.laspalmeras.padel.business.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String dni;
	private String nombreCompleto;
	private String telefono;
	private String email;
	private String sexo; // "Masculino", "Femenino"
	private String estado; // "Alta", "Baja"
	private Boolean lesionado;

	// Getters y setters

	public String getDni() {
		return dni;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public String getSexo() {
		return sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public String isEstado() {
		return estado;
	}

	public boolean isLesionado() {
		return lesionado;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLesionado(boolean lesionado) {
		this.lesionado = lesionado;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}