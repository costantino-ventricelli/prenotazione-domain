package it.links.prenotazione_domain.repository;

import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface Prenotazione2Repository extends JpaRepository<PrenotazioneEntity, Long> {
    boolean existsByUtenteIdAndPostazioneIdAndData(Long utenteId, Long postazioneId, LocalDate data);
}


