package es.laspalmeras.padel.clasificacion.model;

import java.io.Serializable;

import es.laspalmeras.padel.campeonato.model.Campeonato;
import es.laspalmeras.padel.jugador.model.Jugador;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa la clasificación de un jugador en un campeonato.
 */
@Entity
@Table(name = "CLASIFICACION", indexes = {
	    @Index(name = "idx_clasificacion_campeonato", columnList = "campeonato_id"),
	    @Index(name = "idx_clasificacion_jugador", columnList = "jugador_id"),
	    @Index(name = "idx_clasificacion_puntos", columnList = "puntos DESC") // Optimiza consultas de ranking
	})
@Getter
@Setter
@EqualsAndHashCode(of={"campeonato", "jugador"})
public class Clasificacion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campeonato es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campeonato_id", nullable = false)
    private Campeonato campeonato;

    @NotNull(message = "El jugador es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;

    @Min(value = 1, message = "La posición debe ser al menos 1")
    private int posicion;
    
    @Min(value = 0, message = "Los puntos no pueden ser negativos")
    private int puntos;
    
    @Column(name="partidos_jugados")
    @Min(value = 0, message = "Los partidos jugados no pueden ser negativos")
    private int partidosJugados;
    
    @Column(name="partidos_ganados")
    @Min(value = 0, message = "Los partidos ganados no pueden ser negativos")
    private int partidosGanados;
    
    @Column(name="partidos_perdidos")
    @Min(value = 0, message = "Los partidos perdidos no pueden ser negativos")
    private int partidosPerdidos;
    
    @Column(name="sets_ganados")
    @Min(value = 0, message = "Los sets ganados no pueden ser negativos")
    private int setsGanados;
    
    @Column(name="sets_perdidos")
    @Min(value = 0, message = "Los sets perdidos no pueden ser negativos")
    private int setsPerdidos;
    
    @Column(name="juegos_ganados")
    @Min(value = 0, message = "Los juegos ganados no pueden ser negativos")
    private int juegosGanados;
    
    @Column(name="juegos_perdidos")
    @Min(value = 0, message = "Los juegos perdidos no pueden ser negativos")
    private int juegosPerdidos;
    
    // Método para actualizar posición
    @PreUpdate
    @PrePersist
    public void actualizarPosicion() {
        // La posición real se calculará en el servicio mediante consulta
    }
    
    // Método helper para actualizar estadísticas
    public void actualizarEstadisticas(
        int setsGanadosPartido,
        int setsPerdidosPartido,
        int juegosGanadosPartido,
        int juegosPerdidosPartido,
        boolean victoria
    ) {
        this.partidosJugados++;
        
        if (victoria) {
            this.partidosGanados++;
            this.puntos += campeonato.getPuntosPorVictoria();
        } else {
            this.partidosPerdidos++;
            this.puntos += campeonato.getPuntosPorDerrota();
        }
        
        this.setsGanados += setsGanadosPartido;
        this.setsPerdidos += setsPerdidosPartido;
        this.juegosGanados += juegosGanadosPartido;
        this.juegosPerdidos += juegosPerdidosPartido;
    }
}

