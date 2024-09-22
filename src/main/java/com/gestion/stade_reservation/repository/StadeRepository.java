package com.gestion.stade_reservation.repository;

import com.gestion.stade_reservation.entities.Stade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadeRepository extends JpaRepository<Stade, Long> {
}
