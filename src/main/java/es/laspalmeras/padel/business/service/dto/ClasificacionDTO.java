package es.laspalmeras.padel.business.service.dto;

import es.laspalmeras.padel.business.service.model.Campeonato;
import es.laspalmeras.padel.business.service.model.Jugador;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clasificacion")
public class ClasificacionDTO {

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