package com.codegym.checkinhotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class AppRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
//    @ColumnDefault("ROLE_USER")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "roles")
    private List<AppUser> users;

    public AppRole() {
    }

    public AppRole(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role [id=" + id
                + ", name=" + name
                + "]";
    }
}
