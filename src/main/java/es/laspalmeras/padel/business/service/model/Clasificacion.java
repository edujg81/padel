package es.laspalmeras.padel.business.service.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Clasificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Campeonato campeonato;

	@ManyToOne
	private Jugador jugador;

	private int posicion;
	private int puntos;
	private int partidosJugados;
	private int partidosGanados;
	private int partidosPerdidos;
	private int setsGanados;
	private int setsPerdidos;
	private int juegosGanados;
	private int juegosPerdidos;
	private int diferenciaSets;
	private int diferenciaJuegos;

	// Constructores, getters y setters

	public Clasificacion() {
	}

	public Clasificacion(Campeonato campeonato, Jugador jugador, int posicion) {
		this.campeonato = campeonato;
		this.jugador = jugador;
		this.posicion = posicion;
		this.puntos = 0;
		this.partidosJugados = 0;
		this.partidosGanados = 0;
		this.partidosPerdidos = 0;
		this.setsGanados = 0;
		this.setsPerdidos = 0;
		this.juegosGanados = 0;
		this.juegosPerdidos = 0;
		this.diferenciaSets = 0;
		this.diferenciaJuegos = 0;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public int getDiferenciaJuegos() {
		return diferenciaJuegos;
	}

	public int getDiferenciaSets() {
		return diferenciaSets;
	}

	public Long getId() {
		return id;
	}

	public int getJuegosGanados() {
		return juegosGanados;
	}

	public int getJuegosPerdidos() {
		return juegosPerdidos;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public int getPartidosGanados() {
		return partidosGanados;
	}

	public int getPartidosJugados() {
		return partidosJugados;
	}

	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}

	public int getPosicion() {
		return posicion;
	}

	public int getPuntos() {
		return puntos;
	}

	public int getSetsGanados() {
		return setsGanados;
	}

	public int getSetsPerdidos() {
		return setsPerdidos;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public void setDiferenciaJuegos(int diferenciaJuegos) {
		this.diferenciaJuegos = diferenciaJuegos;
	}

	public void setDiferenciaSets(int diferenciaSets) {
		this.diferenciaSets = diferenciaSets;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJuegosGanados(int juegosGanados) {
		this.juegosGanados = juegosGanados;
	}

	public void setJuegosPerdidos(int juegosPerdidos) {
		this.juegosPerdidos = juegosPerdidos;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
	}

	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void setSetsGanados(int setsGanados) {
		this.setsGanados = setsGanados;
	}

	public void setSetsPerdidos(int setsPerdidos) {
		this.setsPerdidos = setsPerdidos;
	}
}