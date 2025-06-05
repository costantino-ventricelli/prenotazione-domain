package it.links.prenotazione_domain.repository;

import it.links.prenotazione_domain.entity.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<UtenteEntity, Long> {
    Optional<UtenteEntity> findByEmail(String email);
}
