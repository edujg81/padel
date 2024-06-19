package es.laspalmeras.padel.business.service.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Jornada jornada;

    @ManyToOne
    private Jugador equipo1Jugador1;

    @ManyToOne
    private Jugador equipo1Jugador2;

    @ManyToOne
    private Jugador equipo2Jugador1;

    @ManyToOne
    private Jugador equipo2Jugador2;

    private String resultado; // Representa el resultado en sets, por ejemplo: "2-1"
    private String pista;
    private LocalDate fecha;

    @ManyToOne
    private Jugador ausente;

    @ManyToOne
    private Jugador lesionado;

    @ManyToOne
    private Jugador sustituto;

    // Nuevos campos para el resultado detallado
    private int juegosGanadosEquipo1;
    private int juegosPerdidosEquipo1;
    private int setsGanadosEquipo1;
    private int setsPerdidosEquipo1;
    private int juegosGanadosEquipo2;
    private int juegosPerdidosEquipo2;
    private int setsGanadosEquipo2;
    private int setsPerdidosEquipo2;

    @ManyToMany
    @JoinTable(
        name = "partido_jugadores",
        joinColumns = @JoinColumn(name = "partido_id"),
        inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private Set<Jugador> jugadores;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Jugador getEquipo1Jugador1() {
        return equipo1Jugador1;
    }

    public void setEquipo1Jugador1(Jugador equipo1Jugador1) {
        this.equipo1Jugador1 = equipo1Jugador1;
    }

    public Jugador getEquipo1Jugador2() {
        return equipo1Jugador2;
    }

    public void setEquipo1Jugador2(Jugador equipo1Jugador2) {
        this.equipo1Jugador2 = equipo1Jugador2;
    }

    public Jugador getEquipo2Jugador1() {
        return equipo2Jugador1;
    }

    public void setEquipo2Jugador1(Jugador equipo2Jugador1) {
        this.equipo2Jugador1 = equipo2Jugador1;
    }

    public Jugador getEquipo2Jugador2() {
        return equipo2Jugador2;
    }

    public void setEquipo2Jugador2(Jugador equipo2Jugador2) {
        this.equipo2Jugador2 = equipo2Jugador2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getPista() {
        return pista;
    }

    public void setPista(String pista) {
        this.pista = pista;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Jugador getAusente() {
        return ausente;
    }

    public void setAusente(Jugador ausente) {
        this.ausente = ausente;
    }

    public Jugador getLesionado() {
        return lesionado;
    }

    public void setLesionado(Jugador lesionado) {
        this.lesionado = lesionado;
    }

    public Jugador getSustituto() {
        return sustituto;
    }

    public void setSustituto(Jugador sustituto) {
        this.sustituto = sustituto;
    }

    public int getJuegosGanadosEquipo1() {
        return juegosGanadosEquipo1;
    }

    public void setJuegosGanadosEquipo1(int juegosGanadosEquipo1) {
        this.juegosGanadosEquipo1 = juegosGanadosEquipo1;
    }

    public int getJuegosPerdidosEquipo1() {
        return juegosPerdidosEquipo1;
    }

    public void setJuegosPerdidosEquipo1(int juegosPerdidosEquipo1) {
        this.juegosPerdidosEquipo1 = juegosPerdidosEquipo1;
    }

    public int getSetsGanadosEquipo1() {
        return setsGanadosEquipo1;
    }

    public void setSetsGanadosEquipo1(int setsGanadosEquipo1) {
        this.setsGanadosEquipo1 = setsGanadosEquipo1;
    }

    public int getSetsPerdidosEquipo1() {
        return setsPerdidosEquipo1;
    }

    public void setSetsPerdidosEquipo1(int setsPerdidosEquipo1) {
        this.setsPerdidosEquipo1 = setsPerdidosEquipo1;
    }

    public int getJuegosGanadosEquipo2() {
        return juegosGanadosEquipo2;
    }

    public void setJuegosGanadosEquipo2(int juegosGanadosEquipo2) {
        this.juegosGanadosEquipo2 = juegosGanadosEquipo2;
    }

    public int getJuegosPerdidosEquipo2() {
        return juegosPerdidosEquipo2;
    }

    public void setJuegosPerdidosEquipo2(int juegosPerdidosEquipo2) {
        this.juegosPerdidosEquipo2 = juegosPerdidosEquipo2;
    }

    public int getSetsGanadosEquipo2() {
        return setsGanadosEquipo2;
    }

    public void setSetsGanadosEquipo2(int setsGanadosEquipo2) {
        this.setsGanadosEquipo2 = setsGanadosEquipo2;
    }

    public int getSetsPerdidosEquipo2() {
        return setsPerdidosEquipo2;
    }

    public void setSetsPerdidosEquipo2(int setsPerdidosEquipo2) {
        this.setsPerdidosEquipo2 = setsPerdidosEquipo2;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
