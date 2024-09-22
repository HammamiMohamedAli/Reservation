package com.gestion.stade_reservation.controller;

import com.gestion.stade_reservation.entities.Stade;
import com.gestion.stade_reservation.entities.User;
import com.gestion.stade_reservation.service.IServiceStade;
import com.gestion.stade_reservation.service.IServiceUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stades")
@CrossOrigin("*")
public class StadeController {

    @Autowired
    IServiceStade iServiceStade;
    @Autowired
    IServiceUtilisateur iServiceUtilisateur;

    @GetMapping("")
    public List<Stade> all() {
        return iServiceStade.findAllStades();
    }

    @PostMapping("/add")
    public ResponseEntity<Long> add(@RequestBody Stade stade ) {
        User user = iServiceUtilisateur.findUserById(stade.getUser().getId());

        stade.setUser(user);

        Stade savedStade = iServiceStade.save(stade);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStade.getId());

    }



    @GetMapping("/{id}")
    public Stade getparid(@PathVariable long id) {
        return iServiceStade.findStadeById(id);
    }



    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        iServiceStade.deleteStade(id);
        return "Suppression réussite";
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody Stade stade) {
        Stade existingStade = iServiceStade.findStadeById(id);
        if (existingStade == null) {
            return ResponseEntity.notFound().build();
        }

        existingStade.setName(stade.getName());
        existingStade.setLocation(stade.getLocation());

        Stade updatedStade = iServiceStade.updateStade(existingStade);
        return ResponseEntity.ok(updatedStade);
    }

}
