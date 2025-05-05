package it.links.prenotazione_domain.controller;

import it.links.prenotazione_domain.dto.AccessoRispostaDTO;
import it.links.prenotazione_domain.service.AccessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accessi")
@RequiredArgsConstructor
public class AccessoController {

    private final AccessoService accessoService;

    @GetMapping("/verifica")
    public AccessoRispostaDTO verificaAccesso(@RequestParam Long utenteId, @RequestParam Long postazioneId) {
        return accessoService.costruisciRispostaAccesso(utenteId, postazioneId);
    }
}
