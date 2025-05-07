package it.links.prenotazione_domain.dto;

import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostazioneDTO {
    private Long id;
    private String nomePostazione;
    private Long stanzaId;
    private List<PrenotazioneEntity> prenotazioni;
}
