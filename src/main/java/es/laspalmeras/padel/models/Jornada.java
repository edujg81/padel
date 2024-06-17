package es.laspalmeras.padel.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    @ManyToOne
    private Campeonato campeonato;

    // Getters y setters
}