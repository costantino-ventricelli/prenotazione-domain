package it.links.prenotazione_domain.controller;

import it.links.prenotazione_domain.dto.AccessoRispostaDTO;
import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import it.links.prenotazione_domain.service.AccessoService;
import it.links.prenotazione_domain.entity.UtenteEntity;
import it.links.prenotazione_domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accessi")
@RequiredArgsConstructor
public class AccessoController {

    private final AccessoService accessoService;
    private final UtenteRepository utenteRepository;

    @GetMapping("/verifica")
    public AccessoRispostaDTO verificaAccesso(@RequestParam Long utenteId, @RequestParam Long postazioneId) {
        boolean accessoConsentito = accessoService.verificaAccesso(utenteId, postazioneId);

        UtenteEntity utente = utenteRepository.findById(utenteId).orElse(null);
        String username = (utente != null) ? utente.getEmail() : "Utente non trovato";
        String ruolo = (utente != null) ? utente.getRuolo().getNome() : "Ruolo non trovato";

        String messaggio = accessoConsentito ? "Accesso consentito" : "Accesso negato";

        return new AccessoRispostaDTO(messaggio, accessoConsentito, username, ruolo);
    }
    @PostMapping("/prenotazioni")
    public ResponseEntity<PrenotazioneEntity> prenotaPostazione(@RequestBody PrenotazioneEntity prenotazione) {
        PrenotazioneEntity createdPrenotazione = accessoService.prenotaPostazione(prenotazione);
        return new ResponseEntity<>(createdPrenotazione, HttpStatus.CREATED);
    }

}
