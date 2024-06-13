package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

    private int position;
    private int points;
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int setsWon;
    private int setsLost;
    private int gamesWon;
    private int gamesLost;

    // Getters y setters
}