package com.gestion.stade_reservation.service;

import com.gestion.stade_reservation.entities.Reservation;
import com.gestion.stade_reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServiceReservation implements IServiceReservation{
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id).get();
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
