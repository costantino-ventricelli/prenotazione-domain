package it.links.prenotazione_domain.service;

import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import it.links.prenotazione_domain.repository.Prenotazione1Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final Prenotazione1Repository prenotazione1Repository;

    public PrenotazioneEntity prenotaPostazione(PrenotazioneEntity prenotazione) {
        boolean sovrapposta = prenotazione1Repository.existsByPostazioneIdAndDataAndOraInizioLessThanAndOraFineGreaterThan(
                prenotazione.getPostazione().getId(),
                prenotazione.getData(),
                prenotazione.getOraFine(),
                prenotazione.getOraInizio()
        );

        if (sovrapposta) {
            throw new IllegalArgumentException("Esiste già una prenotazione per questa postazione in quell'intervallo.");
        }

        return prenotazione1Repository.save(prenotazione);
    }
}
