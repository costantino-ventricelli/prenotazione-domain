package it.links.prenotazione_domain.controller;

import it.links.prenotazione_domain.dto.PrenotazioneDTO;
import it.links.prenotazione_domain.service.PrenotazioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/inserisci")
    public ResponseEntity<?> prenotaPostazione(@RequestBody PrenotazioneDTO prenotazione) {
        return prenotazioneService.prenotaPostazione(prenotazione);
    }

    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<?> eliminaPrenotazione(@PathVariable Long id){
        return prenotazioneService.eliminaPrenotazione(id);
    }

}

