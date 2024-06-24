package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of="id")
@ToString
@Entity
@Table(name="PARTIDO")
public class Partido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "jornada_id")
    private Jornada jornada;

    @ManyToOne
    @JoinColumn(name = "equipo1_jugador1_id")
    private Jugador equipo1Jugador1;

    @ManyToOne
    @JoinColumn(name = "equipo1_jugador2_id")
    private Jugador equipo1Jugador2;

    @ManyToOne
    @JoinColumn(name = "equipo2_jugador1_id")
    private Jugador equipo2Jugador1;

    @ManyToOne
    @JoinColumn(name = "equipo2_jugador2_id")
    private Jugador equipo2Jugador2;

    private String resultado; // Ej: "6-4, 5-7, 6-3"
    private String pista;
   
    @ManyToOne
    private Jugador ausente;

    @ManyToOne
    private Jugador lesionado;

    @ManyToOne
    private Jugador sustituto;

    private Integer juegosGanadosEquipo1Set1;
    private Integer juegosGanadosEquipo2Set1;

    private Integer juegosGanadosEquipo1Set2;
    private Integer juegosGanadosEquipo2Set2;

    private Integer juegosGanadosEquipo1Set3;
    private Integer juegosGanadosEquipo2Set3;

    private String equipoGanador;
}