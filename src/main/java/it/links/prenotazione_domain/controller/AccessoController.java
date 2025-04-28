package it.links.prenotazione_domain.controller;

import it.links.prenotazione_domain.dto.AccessoRispostaDTO;
import it.links.prenotazione_domain.service.AccessoService;
import it.links.prenotazione_domain.entity.UtenteEntity;
import it.links.prenotazione_domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accessi")
@RequiredArgsConstructor
public class AccessoController {

    private final AccessoService accessoService;
    private final UtenteRepository utenteRepository;

    @GetMapping("/verifica")
    public AccessoRispostaDTO verificaAccesso(@RequestParam Long utenteId, @RequestParam Long postazioneId) {
        // Verifica l'accesso
        boolean accessoConsentito = accessoService.verificaAccesso(utenteId, postazioneId);

        // Recupera l'utente per ottenere username e ruolo
        UtenteEntity utente = utenteRepository.findById(utenteId).orElse(null);
        String username = (utente != null) ? utente.getEmail() : "Utente non trovato";
        String ruolo = (utente != null) ? utente.getRuolo().getNome() : "Ruolo non trovato";

        // Crea il messaggio di risposta
        String messaggio = accessoConsentito ? "Accesso consentito" : "Accesso negato";

        // Restituisce un DTO con messaggio, stato, username e ruolo
        return new AccessoRispostaDTO(messaggio, accessoConsentito, username, ruolo);
    }
}
