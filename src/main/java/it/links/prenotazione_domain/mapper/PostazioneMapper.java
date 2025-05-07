package it.links.prenotazione_domain.mapper;

import it.links.prenotazione_domain.dto.PostazioneDTO;
import it.links.prenotazione_domain.entity.PostazioneEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostazioneMapper {
    PostazioneDTO toDto(PostazioneEntity postazione);
    PostazioneEntity toEntity(PostazioneDTO postazioneDTO);
}

