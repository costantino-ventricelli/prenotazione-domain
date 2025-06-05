package it.links.prenotazione_domain.repository;

import it.links.prenotazione_domain.entity.PrenotazioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Repository
public interface PrenotazioneRepository extends JpaRepository<PrenotazioneEntity, Long> {
//    boolean existsByUtenteIdAndPostazioneIdAndData(Long utenteId, Long postazioneId, LocalDate data);
//    boolean existsByPostazioneIdAndDataAndOraInizioLessThanAndOraFineGreaterThan(Long postazioneId, LocalDate data, LocalTime oraFine, LocalTime oraInizio);

    @Query("""
            SELECT COUNT(p) > 0
            FROM PrenotazioneEntity p
            WHERE p.data = :data
            AND (
                (p.oraInizio < :oraFine AND p.oraFine > :oraInizio)
            )
            AND (
                p.utenteId = :utenteId OR p.postazioneId = :postazioneId
            )
    """)
    boolean verificaConflitto(
            @Param("data") LocalDate data,
            @Param("oraInizio") LocalTime oraInizio,
            @Param("oraFine") LocalTime oraFine,
            @Param("utenteId") Long utenteId,
            @Param("postazioneId") Long postazioneId
    );

}


