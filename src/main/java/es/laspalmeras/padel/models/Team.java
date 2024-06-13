package es.laspalmeras.padel.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
      name = "team_player", 
      joinColumns = @JoinColumn(name = "team_id"), 
      inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players;

    // Getters y setters
}