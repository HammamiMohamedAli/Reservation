package com.gestion.stade_reservation.repository;


import com.gestion.stade_reservation.entities.ERole;
import com.gestion.stade_reservation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}