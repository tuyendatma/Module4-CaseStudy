package com.codegym.checkinhotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "hotels")
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column
    private String image;

    @Transient
    private MultipartFile imageFile;

    @Column
    @Type(type = "text")
    private String description;
    //đánh giá
    @Column
//    @Size(min = 1,max = 5)
    private int evaluation;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    @JsonIgnoreProperties(value = "hotels")
    private City city;

    @ManyToOne
    @JoinColumn(name = "hotel_detail_id",nullable = false)
    @JsonIgnoreProperties(value = "hotels")
    private HotelDetails hotelDetails;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "hotel",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "hotels")
    private List<Room> rooms;

    public Hotel() {
    }

    public Hotel(Long id,String name, String address, String image, String description, int evaluation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.evaluation = evaluation;
    }

    public Hotel(String name, String address, String image, String description, int evaluation) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.evaluation = evaluation;
    }

    public Hotel(String name, String address, MultipartFile imageFile, String description, int evaluation) {
        this.name = name;
        this.address = address;
        this.imageFile = imageFile;
        this.description = description;
        this.evaluation = evaluation;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public HotelDetails getHotelDetails() {
        return hotelDetails;
    }

    public void setHotelDetails(HotelDetails hotelDetails) {
        this.hotelDetails = hotelDetails;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

//    @Override
//    public String toString() {
//        return "Hotel [id=" + id
//                + ", name=" + name
//                + ", address=" + address
//                + ", image=" + image
//                + ", description=" + description
//                + ", evaluation=" + evaluation
//                + "]";
//    }
}
