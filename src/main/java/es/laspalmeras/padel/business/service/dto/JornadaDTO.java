package es.laspalmeras.padel.business.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@EqualsAndHashCode(of={"numero", "campeonato"})
@ToString
@Entity
@Table(name="JORNADA")
public class JornadaDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    private LocalDate fechaInicio;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "campeonato_id", nullable = false)
    private CampeonatoDTO campeonato;

    private List<PartidoDTO> partidos;
}