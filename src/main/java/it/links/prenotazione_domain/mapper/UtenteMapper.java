package it.links.prenotazione_domain.mapper;

import it.links.prenotazione_domain.dto.UtenteDTO;
import it.links.prenotazione_domain.entity.UtenteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtenteMapper {
    UtenteDTO toDto(UtenteEntity utente);
    UtenteEntity toEntity(UtenteDTO utenteDTO);
}
