package it.links.prenotazione_domain.repository;

import it.links.prenotazione_domain.entity.PostazioneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostazioneRepository extends JpaRepository<PostazioneEntity, Long> {
}
