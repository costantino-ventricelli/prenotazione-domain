package it.links.prenotazione_domain.service;

import it.links.prenotazione_domain.entity.UtenteEntity;
import it.links.prenotazione_domain.repository.PrenotazioneRepository;
import it.links.prenotazione_domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessoService {

    private final PrenotazioneRepository prenotazioneRepository;
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
        return !prenotazioneRepository.existsByUtenteIdAndPostazioneIdAndData(utenteId, postazioneId, java.time.LocalDate.now());
    }
}
