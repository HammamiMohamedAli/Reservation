package com.gestion.stade_reservation.controller;

import com.gestion.stade_reservation.entities.EReservationStatus;
import com.gestion.stade_reservation.entities.Reservation;
import com.gestion.stade_reservation.service.IServiceReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin("*")
public class ReservationController {


    @Autowired
    private IServiceReservation iServiceReservation;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return iServiceReservation.findAllReservations();
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return iServiceReservation.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        iServiceReservation.deleteReservation(id);
    }
    @PostMapping("/{id}/pay")
    public ResponseEntity<?> payForReservation(@PathVariable Long id) {
        Reservation reservation = iServiceReservation.findReservationById(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }

        reservation.setStatus(EReservationStatus.PAID);
        iServiceReservation.save(reservation);

        return ResponseEntity.ok().build();
    }
}