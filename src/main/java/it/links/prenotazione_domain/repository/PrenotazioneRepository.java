package it.links.prenotazione_domain.repository;

import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEntity, Long> {
    // Aggiungi il metodo custom per verificare l'esistenza di una prenotazione
    boolean existsByUtenteIdAndPostazioneIdAndData(Long utenteId, Long postazioneId, java.time.LocalDate data);
}
