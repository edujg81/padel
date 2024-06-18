package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Jugador jugador;

    @ManyToOne
    private Campeonato campeonato;

    // Getters y setters
}