package it.links.prenotazione_domain.service;

import it.links.prenotazione_domain.dto.AccessoRispostaDTO;
import it.links.prenotazione_domain.dto.UtenteDTO;
import it.links.prenotazione_domain.entity.UtenteEntity;
import it.links.prenotazione_domain.mapper.UtenteMapper;
import it.links.prenotazione_domain.repository.Prenotazione2Repository;
import it.links.prenotazione_domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessoService {

    private final Prenotazione2Repository prenotazione2Repository;
    private final UtenteRepository utenteRepository;
    private final UtenteMapper utenteMapper;

    public boolean verificaAccesso(Long utenteId, Long postazioneId) {
        UtenteEntity utente = utenteRepository.findById(utenteId).orElse(null);
        if (utente == null) return false;

        if ("admin".equals(utente.getRuolo().getNome())) return true;

        return prenotazione2Repository.existsByUtenteIdAndPostazioneIdAndData(
                utenteId, postazioneId, LocalDate.now());
    }

    public AccessoRispostaDTO costruisciRispostaAccesso(Long utenteId, Long postazioneId) {
        boolean accessoConsentito = verificaAccesso(utenteId, postazioneId);

        UtenteEntity utenteEntity = utenteRepository.findById(utenteId).orElse(null);
        UtenteDTO utente = Optional.ofNullable(utenteEntity)
                .map(utenteMapper::toDto)
                .orElse(null);

        String username = utente != null ? utente.getEmail() : "Utente non trovato";
        String ruolo = utenteEntity != null && utenteEntity.getRuolo() != null
                ? utenteEntity.getRuolo().getNome()
                : "Ruolo non trovato";

        String messaggio = accessoConsentito ? "Accesso consentito" : "Accesso negato";

        return new AccessoRispostaDTO(messaggio, accessoConsentito, username, ruolo);
    }
}
