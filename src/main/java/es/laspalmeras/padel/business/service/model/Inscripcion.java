package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import es.laspalmeras.padel.jugador.Jugador;
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

/**
 * Representa una inscripción de un jugador a un campeonato.
 */
@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of={"jugador", "campeonato"})
@ToString
@Entity
@Table(name="INSCRIPCION")
public class Inscripcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "campeonato_id", nullable = false)
    private Campeonato campeonato;
    
    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    @JsonIgnoreProperties("inscripcion")
    private Jugador jugador;
}