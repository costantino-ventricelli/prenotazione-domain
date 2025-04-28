package it.links.prenotazione_domain.repository;

import it.links.prenotazione_domain.entity.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<UtenteEntity, Long> {
}
