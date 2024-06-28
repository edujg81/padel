package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@Getter
@Setter
@EqualsAndHashCode(of={"campeonato", "jugador"})
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CLASIFICACION")
public class Clasificacion implements Serializable {

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

    // Getters, setters, equals y hashCode generados automáticamente por Lombok
}

