package es.laspalmeras.padel.models;

import javax.persistence.*;

@Entity
public class Substitution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Partido match;

    @ManyToOne
    @JoinColumn(name = "absent_player_id")
    private Jugador absentPlayer;

    @ManyToOne
    @JoinColumn(name = "substitute_player_id")
    private Jugador substitutePlayer;

    private String reason; // "Lesión", "Ausencia"

    // Getters y setters
}