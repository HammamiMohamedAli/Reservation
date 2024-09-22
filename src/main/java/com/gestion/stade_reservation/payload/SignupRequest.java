package com.gestion.projet_gestion_annonces.payload;


import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;


    @Pattern(regexp="\\d{8}", message="Le numéro de téléphone doit contenir 8 chiffres")
    private String phoneNumber;
    private Set<String> role;
    /*private String photo;*/

   /* public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }*/


    @NotBlank
    @Size(min = 6, max = 40)
    private String password;




    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
