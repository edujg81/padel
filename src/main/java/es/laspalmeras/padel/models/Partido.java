package es.laspalmeras.padel.models;

import javax.persistence.*;

//import org.hibernate.mapping.Set;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.Set;

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
    private String resultado;

    // Getters y setters
}
