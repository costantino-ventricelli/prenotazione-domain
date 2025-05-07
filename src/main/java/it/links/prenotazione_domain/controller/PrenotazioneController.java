package it.links.prenotazione_domain.controller;

import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import it.links.prenotazione_domain.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @PostMapping
    public ResponseEntity<PrenotazioneEntity> prenotaPostazione(@RequestBody PrenotazioneEntity prenotazione) {
        PrenotazioneEntity createdPrenotazione = prenotazioneService.prenotaPostazione(prenotazione);
        return new ResponseEntity<>(createdPrenotazione, HttpStatus.CREATED);
    }
}

