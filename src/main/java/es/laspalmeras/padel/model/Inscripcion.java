package es.laspalmeras.padel.model;

import java.io.Serializable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Getter
@Setter
@EqualsAndHashCode(of={"jugador", "campeonato"})
@ToString
@Entity
@Table(name="INSCRIPCION")
@EntityListeners(AuditingEntityListener.class)
public class Inscripcion implements Serializable {

	private static final long serialVersionUID = 1L;

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
    
//    @CreatedDate
//    private LocalDate fechaInscripcion;
//
//    @LastModifiedDate
//    private LocalDate fechaDesinscripcion;
}