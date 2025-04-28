package it.links.prenotazione_domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "prenotazioni")
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private LocalDateTime oraInizio;
    private LocalDateTime oraFine;

    @ManyToOne
    @JoinColumn(name = "utenti_id")
    private UtenteEntity utente;

    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private PostazioneEntity postazione;
}



