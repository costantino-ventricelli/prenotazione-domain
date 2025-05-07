package it.links.prenotazione_domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDTO {
    private Long id;
    private String cognome;
    private String nome;
    private String email;
    private String percorsoFoto;
    private Year annoAssunzione;
    private Long ruoloId;
}
