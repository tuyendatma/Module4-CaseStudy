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
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_detail_id",nullable = false)
    private RoomDetails roomDetails;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "room",cascade = CascadeType.ALL)
    private List<RoomBooking>roomBookings;
}
