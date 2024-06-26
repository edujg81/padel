package es.laspalmeras.padel.business.service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clasificacion")
public class Clasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campeonato_id", nullable = false)
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;

    private int posicion;
    private int puntos;
    
    @Column(name="partidos_jugados")
    private int partidosJugados;
    
    @Column(name="partidos_ganados")
    private int partidosGanados;
    
    @Column(name="partidos_perdidos")
    private int partidosPerdidos;
    
    @Column(name="sets_ganados")
    private int setsGanados;
    
    @Column(name="sets_perdidos")
    private int setsPerdidos;
    
    @Column(name="juegos_ganados")
    private int juegosGanados;
    
    @Column(name="juegos_perdidos")
    private int juegosPerdidos;
}