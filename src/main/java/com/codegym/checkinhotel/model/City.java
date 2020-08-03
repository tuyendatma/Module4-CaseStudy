package com.codegym.checkinhotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(nullable = false,unique = true)
    private String name;
    @Column
    @Type(type = "text")
    private String description;
    @Column
    private String image;

    @Transient
    private MultipartFile imageFile;

//    targetEntity = Hotel.class  mappedBy = "city"
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL},targetEntity = Hotel.class)
    @JsonIgnoreProperties(value = "cities")
    private List<Hotel> hotels;

    public City(Long id,String name,String description, String image){
        this.id=id;
        this.name=name;
        this.description=description;
        this.image =image;
    }

    public City(String name, String description,String image){
        this.name =name;
        this.description =description;
        this.image = image;
    }

    public City(String name, String description, MultipartFile imageFile){
        this.name = name;
        this.description =description;
        this.imageFile = imageFile;
    }


//    @Override
//    public String toString() {
//        return "City [id=" + id
//                + ", name=" + name
//                + ", description=" + description
//                + ", image=" + image
//                + "]";
//    }
}
