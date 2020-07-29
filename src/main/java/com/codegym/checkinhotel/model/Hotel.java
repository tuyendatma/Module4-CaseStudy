package com.codegym.checkinhotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "hotels")
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
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
    @Size(min = 1,max = 5)
    private int evaluation;

    @ManyToOne
    @JoinColumn(name = "city_id",nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "hotel_detail_id",nullable = false)
    private HotelDetails hotelDetails;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "hotel",cascade = CascadeType.ALL)
    private List<Room> rooms;

    @Override
    public String toString() {
        return "Hotel [id=" + id
                + ", name=" + name
                + ", address=" + address
                + ", description=" + description
                + ", evaluation=" + evaluation
                + "]";
    }
}
