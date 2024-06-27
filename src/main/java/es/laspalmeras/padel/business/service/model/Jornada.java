package es.laspalmeras.padel.business.service.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of={"numero", "campeonato"})
@ToString
@Entity
@Table(name="JORNADA")
public class Jornada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    private LocalDate fechaInicio;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "campeonato_id", nullable = false)
    private Campeonato campeonato;

   /* 
    @OneToMany(mappedBy = "jornada", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnoreProperties("jornada")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Partido> partidos;*/
}