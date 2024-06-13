package es.laspalmeras.padel.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MatchDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int dayNumber;

    @OneToMany(mappedBy = "matchDay")
    private Set<Match> matches;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

    // Getters y setters
}