package com.gestion.stade_reservation.service;

import com.gestion.stade_reservation.entities.Reservation;
import com.gestion.stade_reservation.entities.Stade;

import java.util.List;

public interface IServiceReservation {
    public Reservation save(Reservation reservation);
    public List<Reservation> findAllReservations();
    Reservation findReservationById(Long id);
    public void deleteReservation(Long id);
}
