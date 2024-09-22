package com.gestion.stade_reservation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String name ;

     private String   location ;

    @ManyToOne

    private User user;

    @OneToMany(mappedBy = "stade", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;
}
