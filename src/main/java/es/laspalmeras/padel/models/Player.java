package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String fullName;
    private String phone;
    private String email;
    private String gender; // "Masculino", "Femenino"
    private String status; // "Alta", "Baja"
    private boolean injured;

    // Getters y setters
}
