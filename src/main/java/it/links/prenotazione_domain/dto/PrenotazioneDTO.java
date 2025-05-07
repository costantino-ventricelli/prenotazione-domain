package it.links.prenotazione_domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDTO {
    private Long id;
    private LocalDate data;
    private LocalDateTime oraInizio;
    private LocalDateTime oraFine;
    private Long utenteId;
    private Long postazioneId;
}
