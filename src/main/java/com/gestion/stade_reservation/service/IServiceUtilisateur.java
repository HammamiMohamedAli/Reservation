package com.gestion.stade_reservation.service;

import com.gestion.stade_reservation.entities.User;

import java.util.List;
import java.util.Optional;

public interface IServiceUtilisateur {
    public User save(User user);
    public User findUserById(Long id);


    public List<User> findAllUsers();
    public User updateUser(User user);
    public void deleteUser(Long id);
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
