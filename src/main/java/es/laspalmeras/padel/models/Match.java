package es.laspalmeras.padel.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    private int scoreTeam1;
    private int scoreTeam2;
    private String court;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "match_day_id")
    private MatchDay matchDay;

    @OneToMany(mappedBy = "match")
    private Set<Substitution> substitutions;

    // Getters y setters
}
