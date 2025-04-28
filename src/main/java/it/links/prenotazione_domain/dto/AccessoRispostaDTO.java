package it.links.prenotazione_domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessoRispostaDTO {
    private String messaggio;
    private boolean accessoConsentito;
    private String username;
    private String ruolo;
}