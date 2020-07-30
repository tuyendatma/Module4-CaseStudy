package com.codegym.checkinhotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    @Type(type = "text")
    private String description;
    @Column
    private String image;

    @Transient
    private MultipartFile imageFile;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "city",cascade = CascadeType.ALL,targetEntity = Hotel.class)
    private List<Hotel> hotels;

    @Override
    public String toString() {
        return "City [id=" + id
                + ", name=" + name
                + ", description=" + description
                + ", image=" + image
                + "]";
    }
}
