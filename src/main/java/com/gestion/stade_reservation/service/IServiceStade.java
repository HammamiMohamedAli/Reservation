package com.gestion.stade_reservation.service;

import com.gestion.stade_reservation.entities.Stade;

import java.util.List;

public interface IServiceStade {
    public Stade save(Stade stade);
    public Stade findStadeById(Long id);


    public List<Stade> findAllStades();
    public void deleteStade(Long id);
    public Stade updateStade(Stade stade);
}
