package com.codegym.checkinhotel.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class AppRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @ColumnDefault("ROLE_USER")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role",cascade = CascadeType.ALL)
    private List<AppUser> users;

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Hotel [id=" + id
                + ", name=" + name
                + "]";
    }
}
