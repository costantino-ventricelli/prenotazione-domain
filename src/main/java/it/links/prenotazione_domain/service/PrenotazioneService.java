package it.links.prenotazione_domain.service;

import it.links.prenotazione_domain.dto.PrenotazioneDTO;
import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import it.links.prenotazione_domain.mapper.PrenotazioneMapper;
import it.links.prenotazione_domain.repository.PostazioneRepository;
import it.links.prenotazione_domain.repository.Prenotazione2Repository;
import it.links.prenotazione_domain.repository.UtenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final Prenotazione2Repository prenotazione2Repository;
    private final PrenotazioneMapper prenotazioneMapper;
    private final UtenteRepository utenteRepository;
    private final PostazioneRepository postazioneRepository;

    public PrenotazioneDTO prenotaPostazione(PrenotazioneDTO dto) {
        PrenotazioneEntity prenotazione = prenotazioneMapper.toEntity(dto);

        prenotazione.setUtente(utenteRepository.findById(dto.getUtenteId()).orElseThrow());
        prenotazione.setPostazione(postazioneRepository.findById(dto.getPostazioneId()).orElseThrow());

        boolean sovrapposta = prenotazione2Repository
                .existsByPostazioneIdAndDataAndOraInizioLessThanAndOraFineGreaterThan(
                        dto.getPostazioneId(), dto.getData(), dto.getOraFine(), dto.getOraInizio());

        if (sovrapposta) {
            throw new IllegalArgumentException("Esiste già una prenotazione per questa postazione in quell'intervallo.");
        }

        PrenotazioneEntity saved = prenotazione2Repository.save(prenotazione);
        return prenotazioneMapper.toDto(saved);
    }
}

