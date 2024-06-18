package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // Masculina, Femenina, Mixta

    // Getters y setters
}