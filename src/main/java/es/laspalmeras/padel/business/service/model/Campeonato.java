package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of={"año", "categoria", "division"})
@ToString
@Entity
@Table(name="CAMPEONATO")
public class Campeonato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer año;
    private String categoria; // "Masculino", "Femenino", "Mixto"
    private Integer division;
    private String estado; // "Sin iniciar", "En curso", "Finalizado"
    private Boolean activo;
}