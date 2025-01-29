package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Representa a un jugador registrado en el Club de Padel
 */
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

    @NotBlank
    @Size(max = 9)
    private String dni;
    
    @NotBlank
    @Size(max = 255)
    private String nombreCompleto;
    
    @Size(max = 9)
    private String telefono;
    
    @Email
    @Size(max = 100)
    private String email;
    
    @NotBlank
    @Pattern(regexp = "Masculino|Femenino", message = "Sexo debe ser 'Masculino' o 'Femenino'")
    private String sexo; // "Masculino", "Femenino"
    
    @NotBlank
    @Pattern(regexp = "Alta|Baja", message = "Estado debe ser 'Alta' o 'Baja'")
    private String estado; // "Alta", "Baja"
    
    @NotNull
    private Boolean lesionado;
    
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}