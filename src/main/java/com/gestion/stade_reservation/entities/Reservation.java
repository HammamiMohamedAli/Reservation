package com.gestion.stade_reservation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime reservationDate ;
    @Enumerated(EnumType.STRING)
    private EReservationStatus status = EReservationStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stade_id")
    private Stade stade;
}
