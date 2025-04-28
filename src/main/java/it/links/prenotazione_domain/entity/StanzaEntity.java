package it.links.prenotazione_domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "stanze")
@NoArgsConstructor
@AllArgsConstructor
public class StanzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_sede")
    private SedeEntity sede;

    @JsonBackReference
    @OneToMany(mappedBy = "stanza")
    private List<PostazioneEntity> postazioni;
}

