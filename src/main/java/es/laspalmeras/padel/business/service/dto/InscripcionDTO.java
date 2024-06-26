package es.laspalmeras.padel.business.service.dto;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of={"jugador", "campeonato"})
@ToString
@Entity
@Table(name="INSCRIPCION")
public class InscripcionDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CampeonatoDTO campeonato;
    
    @ManyToOne
    private JugadorDTO jugador;
}