package es.laspalmeras.padel.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int año;
    private boolean activo;

    @ManyToOne
    private Categoria categoria; // "Masculina", "Femenina", "Mixta"

    @ManyToOne
    private Division division; // "Primera", "Segunda", "Tercera"

    @OneToMany(mappedBy = "campeonato")
    private List<Jornada> jornadas;

    // Getters y setters
}
