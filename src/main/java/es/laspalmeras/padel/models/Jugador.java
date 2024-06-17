package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String sexo; // "Masculino", "Femenino"
    private String estado; // "Alta", "Baja"
    private boolean lesionado;

    // Getters y setters
}
