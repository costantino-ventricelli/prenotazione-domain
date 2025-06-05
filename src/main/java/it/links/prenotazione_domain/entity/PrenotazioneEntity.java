package it.links.prenotazione_domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "prenotazioni")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrenotazioneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private LocalTime oraInizio;
    private LocalTime oraFine;

    @Column(name = "utenti_id")
    private Long utenteId;

    @Column(name = "postazione_id")
    private Long postazioneId;
}



