package com.codegym.checkinhotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel_details")
@NoArgsConstructor
@AllArgsConstructor
public class HotelDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column
//    @Type(type = "text")
    private Integer quantity;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "hotelDetails",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "hotel_details")
    private List<Hotel> hotels;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public String toString() {
        return "Hotel detail [id=" + id
                + ", name=" + name
                + ", description=" + quantity
                + "]";
    }
}
