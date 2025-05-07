package it.links.prenotazione_domain.mapper;

import it.links.prenotazione_domain.dto.PrenotazioneDTO;
import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrenotazioneMapper {
    PrenotazioneDTO toDto(PrenotazioneEntity prenotazione);
    PrenotazioneEntity toEntity(PrenotazioneDTO prenotazioneDTO);
}
