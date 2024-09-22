package com.gestion.stade_reservation.repository;


import com.gestion.stade_reservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
