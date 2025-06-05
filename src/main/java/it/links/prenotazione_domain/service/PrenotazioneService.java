package it.links.prenotazione_domain.service;

import it.links.prenotazione_domain.dto.ErroreDTO;
import it.links.prenotazione_domain.dto.PrenotazioneDTO;
import it.links.prenotazione_domain.entity.PostazioneEntity;
import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import it.links.prenotazione_domain.entity.UtenteEntity;
import it.links.prenotazione_domain.repository.PostazioneRepository;
import it.links.prenotazione_domain.repository.PrenotazioneRepository;
import it.links.prenotazione_domain.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    public ResponseEntity<?> prenotaPostazione(PrenotazioneDTO dto){
        Optional<UtenteEntity> utente = utenteRepository.findByEmail(dto.getEmail());
        Optional<PostazioneEntity> postazione = postazioneRepository.findById(dto.getPostazioneId());

        if(utente.isEmpty() || postazione.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroreDTO("Utente non trovato o id postazione non valida."));
        }

        boolean conflitto = prenotazioneRepository.verificaConflitto(
                dto.getData(),
                dto.getOraInizio(),
                dto.getOraFine(),
                utente.get().getId(),
                dto.getPostazioneId()
        );

        if(!conflitto) {
            PrenotazioneEntity prenotazione = PrenotazioneEntity.builder()
                    .data(dto.getData())
                    .oraInizio(dto.getOraInizio())
                    .oraFine(dto.getOraFine())
                    .utenteId(utente.get().getId())
                    .postazioneId(dto.getPostazioneId())
                    .build();

            prenotazioneRepository.save(prenotazione);
            dto.setMessaggio("insert_success");
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErroreDTO("Postazione già occupata o orario incompatibile con altre prenotazioni."));
        }
    }

    public ResponseEntity<?> eliminaPrenotazione(Long id){
        Optional<PrenotazioneEntity> prenotazione = prenotazioneRepository.findById(id);

        if (prenotazione.isEmpty()){
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                     .body(new ErroreDTO("reservation_not_found"));
         }

        prenotazioneRepository.delete(prenotazione.get());
        return ResponseEntity.ok().build();
    }

}

