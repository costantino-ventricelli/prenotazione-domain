package it.links.prenotazione_domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDTO {
    @JsonIgnore
    private Long id;

    private LocalDate data;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime oraInizio;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime oraFine;

    private String email;
    private Long postazioneId;

    private String messaggio;
}
