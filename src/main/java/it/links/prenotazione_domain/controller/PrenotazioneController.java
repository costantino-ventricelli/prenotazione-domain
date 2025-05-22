package it.links.prenotazione_domain.controller;

import it.links.prenotazione_domain.dto.PrenotazioneDTO;
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
    public ResponseEntity<PrenotazioneDTO> prenotaPostazione(@RequestBody PrenotazioneDTO prenotazione) {
        PrenotazioneDTO createdPrenotazione = prenotazioneService.prenotaPostazione(prenotazione);
        return new ResponseEntity<>(createdPrenotazione, HttpStatus.CREATED);
    }
}

