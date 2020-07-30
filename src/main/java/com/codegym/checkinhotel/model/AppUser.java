package com.codegym.checkinhotel.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    private Boolean gender;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private AppRole role;

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
