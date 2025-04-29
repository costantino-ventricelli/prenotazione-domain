package it.links.prenotazione_domain.service;

import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import it.links.prenotazione_domain.entity.UtenteEntity;
import it.links.prenotazione_domain.repository.Prenotazione1Repository;
import it.links.prenotazione_domain.repository.Prenotazione2Repository;
import it.links.prenotazione_domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessoService {

    private final Prenotazione1Repository prenotazione1Repository;
    private final Prenotazione2Repository prenotazione2Repository;
    private final UtenteRepository utenteRepository;

    public boolean verificaAccesso(Long utenteId, Long postazioneId) {
        // Verifica che l'utente esista
        UtenteEntity utente = utenteRepository.findById(utenteId).orElse(null);
        if (utente == null) {
            return false; // Utente non trovato
        }

        // Verifica se l'utente ha il permesso (ad esempio, solo gli admin possono prenotare)
        if (utente.getRuolo().getNome().equals("admin")) {
            return true; // Gli admin possono prenotare ovunque
        }

        // Verifica se l'utente ha una prenotazione per quella postazione nella data odierna
        return !prenotazione2Repository.existsByUtenteIdAndPostazioneIdAndData(utenteId, postazioneId, java.time.LocalDate.now());
    }
    //controllo se la postazione è libera prenota altrimenti avvisa che è già occupata
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

