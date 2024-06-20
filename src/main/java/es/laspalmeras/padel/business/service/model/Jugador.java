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
@EqualsAndHashCode(of="dni")
@ToString
@Entity
@Table(name="JUGADOR")
public class Jugador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String sexo; // "Masculino", "Femenino"
    private String estado; // "Alta", "Baja"
    private Boolean lesionado;
}