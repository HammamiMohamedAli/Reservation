package com.gestion.stade_reservation.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import com.gestion.projet_gestion_annonces.payload.JwtResponse;
import com.gestion.projet_gestion_annonces.payload.LoginRequest;
import com.gestion.projet_gestion_annonces.payload.MessageResponse;
import com.gestion.projet_gestion_annonces.payload.SignupRequest;



import com.gestion.stade_reservation.entities.ERole;
import com.gestion.stade_reservation.entities.Role;
import com.gestion.stade_reservation.entities.User;
import com.gestion.stade_reservation.jwt.JwtUtils;
import com.gestion.stade_reservation.repository.RoleRepository;
import com.gestion.stade_reservation.security.UserDetailsImpl;
import com.gestion.stade_reservation.service.IServiceUtilisateur;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IServiceUtilisateur iServiceUtilisateur;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPhoneNumber(),
                /* userDetails.getPhoto(),*/
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (iServiceUtilisateur.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (iServiceUtilisateur.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
      /*  if (!file.isEmpty()) {
            try {
                String uploadDir = "/Projet_Gestion_Annonces/src/main/resources/uploads";
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.badRequest().body(new MessageResponse("Failed to upload image!"));
                }

                signUpRequest.setPhoto(uploadPath.resolve(fileName).toString());
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body(new MessageResponse("Failed to upload image!"));
            }
        }*/
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getPhoneNumber());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        iServiceUtilisateur.save(user);
/*
        try {
            iServiceUtilisateur.uploadPhoto(user.getId(), file);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new MessageResponse("Failed to upload image!"));
        }*/
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
