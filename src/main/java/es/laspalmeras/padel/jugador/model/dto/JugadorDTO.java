package es.laspalmeras.padel.jugador.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JugadorDTO {
	private Long id;

    @NotBlank
    @Size(max = 9)
    @Pattern(regexp = "\\d{8}[A-HJ-NP-TV-Z]", message = "DNI debe tener 8 dígitos seguidos de una letra válida")
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
    private String sexo;

    @NotBlank
    @Pattern(regexp = "Alta|Baja", message = "Estado debe ser 'Alta' o 'Baja'")
    private String estado;

    @NotNull
    private Boolean lesionado;

    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    
    public JugadorDTO() {}
    
    public JugadorDTO(Long id, String nombreCompleto) {
    	this.id = id;
        this.nombreCompleto = nombreCompleto;
	}
}