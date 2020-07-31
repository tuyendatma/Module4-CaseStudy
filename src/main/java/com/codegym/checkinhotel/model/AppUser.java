package com.codegym.checkinhotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;
    private @NotEmpty String gender;

    @ManyToOne
    @JoinColumn(name = "role_id" , nullable = false)
    @JsonIgnoreProperties(value = "users")
    private AppRole role;

    public AppUser() {
    }

    public AppUser(Long id,String name,String email,String gender,String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    public AppUser(String name,String email,String gender,String username, String password) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @NotEmpty String getGender() {
        return gender;
    }

    public void setGender(@NotEmpty String gender) {
        this.gender = gender;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id
                + ", name=" + name
                + ", email=" + email
                + ", gender=" + gender
                + ", username=" + username
                + ", password=" + password
                + "]";
    }
}
