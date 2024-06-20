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

    private String resultado; // Puede ser nulo hasta que se registre un resultado
}