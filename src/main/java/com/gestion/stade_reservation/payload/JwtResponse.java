package com.gestion.projet_gestion_annonces.payload;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String phoneNumber;
   /* private String photo;*/
    private String email;
    private List<String> roles;



    public JwtResponse(String accessToken, Long id, String username, String email, String phoneNumber/*, String photo*/, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.phoneNumber=phoneNumber;
        /*this.photo=photo;*/
        this.email = email;
        this.roles = roles;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccessToken() {
        return token;
    }

   /* public void setPhoto(String photo) {
        this.photo = photo;
    }*/

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber(){return phoneNumber;}
   /* public String getPhoto() {
        return photo;
    }*/

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
