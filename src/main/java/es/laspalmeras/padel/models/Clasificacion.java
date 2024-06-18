package es.laspalmeras.padel.models;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getSetsGanados() {
        return setsGanados;
    }

    public void setSetsGanados(int setsGanados) {
        this.setsGanados = setsGanados;
    }

    public int getSetsPerdidos() {
        return setsPerdidos;
    }

    public void setSetsPerdidos(int setsPerdidos) {
        this.setsPerdidos = setsPerdidos;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public void setJuegosGanados(int juegosGanados) {
        this.juegosGanados = juegosGanados;
    }

    public int getJuegosPerdidos() {
        return juegosPerdidos;
    }

    public void setJuegosPerdidos(int juegosPerdidos) {
        this.juegosPerdidos = juegosPerdidos;
    }

    public int getDiferenciaSets() {
        return diferenciaSets;
    }

    public void setDiferenciaSets(int diferenciaSets) {
        this.diferenciaSets = diferenciaSets;
    }

    public int getDiferenciaJuegos() {
        return diferenciaJuegos;
    }

    public void setDiferenciaJuegos(int diferenciaJuegos) {
        this.diferenciaJuegos = diferenciaJuegos;
    }
}