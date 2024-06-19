package es.laspalmeras.padel.business.service.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int año;
    private boolean activo;

    @ManyToOne
    @Enumerated(EnumType.STRING)
    private Categoria categoria; // "Masculina", "Femenina", "Mixta"

    @ManyToOne
    @Enumerated(EnumType.STRING)
    private Division division; // "Primera", "Segunda", "Tercera"

    @OneToMany(mappedBy = "campeonato")
    private Set<Jornada> jornadas = new HashSet<>();

    // Constructor vacío y constructor con parámetros (si es necesario)

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(Set<Jornada> jornadas) {
        this.jornadas = jornadas;
    }
}