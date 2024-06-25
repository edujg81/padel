package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @JoinColumn(name = "jornada_id", nullable = false)
    private Jornada jornada;

    @ManyToOne
    @JoinColumn(name = "equipo1_jugador1_id", nullable = false)
    private Jugador equipo1Jugador1;

    @ManyToOne
    @JoinColumn(name = "equipo1_jugador2_id", nullable = false)
    private Jugador equipo1Jugador2;

    @ManyToOne
    @JoinColumn(name = "equipo2_jugador1_id", nullable = false)
    private Jugador equipo2Jugador1;

    @ManyToOne
    @JoinColumn(name = "equipo2_jugador2_id", nullable = false)
    private Jugador equipo2Jugador2;

    private String resultado = "No jugado"; // Ej: "6-4, 5-7, 6-3"
    private String pista = "Sin asignar";

    @OneToMany(mappedBy = "partido")
    private List<Ausencia> ausencias;

    @Column(name = "juegos_ganados_equipo1_set1")
    private Integer juegosGanadosEquipo1Set1;
    
    @Column(name = "juegos_ganados_equipo2_set1")
    private Integer juegosGanadosEquipo2Set1;

    @Column(name = "juegos_ganados_equipo1_set2")
    private Integer juegosGanadosEquipo1Set2;
    
    @Column(name = "juegos_ganados_equipo2_set2")
    private Integer juegosGanadosEquipo2Set2;

    @Column(name = "juegos_ganados_equipo1_set3")
    private Integer juegosGanadosEquipo1Set3;
    
    @Column(name = "juegos_ganados_equipo2_set3")
    private Integer juegosGanadosEquipo2Set3;

    private String equipoGanador = "Ninguno";
    
    private Boolean registrado = false;
}