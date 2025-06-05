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

    @Column(name = "stanza_id")
    private Long stanzaId;
}
