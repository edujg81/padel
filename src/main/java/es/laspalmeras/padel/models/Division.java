package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Primera, Segunda, Tercera

    // Getters y setters
}
