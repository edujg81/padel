package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Substitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "absent_player_id")
    private Player absentPlayer;

    @ManyToOne
    @JoinColumn(name = "substitute_player_id")
    private Player substitutePlayer;

    private String reason; // "Lesión", "Ausencia"

    // Getters y setters
}