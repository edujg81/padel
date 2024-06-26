package es.laspalmeras.padel.business.service.dto;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Data
@NoArgsConstructor
@Entity
@Table(name = "AUSENCIA")
public class AusenciaDTO implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "partido_id", nullable = false)
    private PartidoDTO partido;

    @ManyToOne
    @JoinColumn(name = "ausente_id", nullable = false)
    private JugadorDTO ausente;

    @ManyToOne
    @JoinColumn(name = "sustituto_id", nullable = false)
    private JugadorDTO sustituto;
}