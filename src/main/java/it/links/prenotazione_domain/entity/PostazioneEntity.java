package it.links.prenotazione_domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Data
@Table(name = "postazioni")
@NoArgsConstructor
@AllArgsConstructor
public class PostazioneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePostazione;

    @ManyToOne
    @JoinColumn(name = "stanza_id")
    private StanzaEntity stanza;

    @JsonBackReference
    @OneToMany(mappedBy = "postazione")
    private List<PrenotazioneEntity> prenotazioni;
}
