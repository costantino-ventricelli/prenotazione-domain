package it.links.prenotazione_domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity
@Table(name = "utenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cognome;
    private String nome;
    private String password;
    private String email;

    @Column(name = "percorso_foto", columnDefinition = "TEXT")
    private String percorsoFoto;

    @Column(name = "anno_assunzione")
    private Year annoAssunzione;

    @Column(name = "ruolo_id")
    private Integer ruoloId;
}


