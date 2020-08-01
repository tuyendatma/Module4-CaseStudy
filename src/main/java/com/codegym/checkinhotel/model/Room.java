package com.codegym.checkinhotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long price;
    @Column
    private String image;

    @Transient
    private MultipartFile imageFile;

    @Column
    @Type(type = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnoreProperties(value = "rooms")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_detail_id", nullable = false)
    @JsonIgnoreProperties(value = "rooms")
    private RoomDetails roomDetails;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room",cascade = CascadeType.ALL)
    private List<RoomBooking>roomBookings;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "room_booking_room",
//            joinColumns = {@JoinColumn(name = "room_id")},
//            inverseJoinColumns = {@JoinColumn(name = "room_booking_id")})
//    @JsonIgnoreProperties(value = "rooms")
//    private List<RoomBooking> roomBookings;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public RoomDetails getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }

    public List<RoomBooking> getRoomBookings() {
        return roomBookings;
    }

    public void setRoomBookings(List<RoomBooking> roomBookings) {
        this.roomBookings = roomBookings;
    }

    @Override
    public String toString() {
        return "Room [id=" + id
                + ", name=" + name
                + ", price=" + price
                + ", image=" + image
                + ", description=" + description
                + "]";
    }
}
