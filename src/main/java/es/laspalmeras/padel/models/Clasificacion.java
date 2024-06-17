package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Clasificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Jugador jugador;
    @ManyToOne
    private Campeonato campeonato;
    private int posicion;
    private int puntos;
    private int partidosJugados;
    private int partidosGanados;
    private int partidosPerdidos;
    private int diferenciaSets;
    private int setsGanados;
    private int setsPerdidos;
    private int diferenciaJuegos;
    private int juegosGanados;
    private int juegosPerdidos;

    // Getters y setters
}