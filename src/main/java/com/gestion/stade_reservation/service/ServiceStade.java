package com.gestion.stade_reservation.service;

import com.gestion.stade_reservation.entities.Stade;
import com.gestion.stade_reservation.repository.StadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStade implements IServiceStade {
    @Autowired
    StadeRepository stadeRepository;
    @Override
    public Stade save(Stade stade) {
        return stadeRepository.save(stade) ;
    }

    @Override
    public Stade findStadeById(Long id) {
        return stadeRepository.findById(id).get();
    }

    @Override
    public List<Stade> findAllStades() {
        return stadeRepository.findAll();
    }

    @Override
    public void deleteStade(Long id) {
        stadeRepository.deleteById((long) id);
    }

    @Override
    public Stade updateStade(Stade stade) {
        return stadeRepository.save(stade);
    }
}
