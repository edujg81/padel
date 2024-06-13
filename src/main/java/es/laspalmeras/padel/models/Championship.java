package es.laspalmeras.padel.models;

import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category; // "Masculina", "Femenina", "Mixta"
    private String division; // "Primera", "Segunda", "Tercera"
    private boolean active;
    private int year;

    @OneToMany(mappedBy = "championship")
    private Set<Match> matches;

    @ManyToMany
    @JoinTable(
      name = "championship_player", 
      joinColumns = @JoinColumn(name = "championship_id"), 
      inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players;

    // Getters y setters
}
