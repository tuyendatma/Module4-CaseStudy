package com.codegym.checkinhotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "hotel_details")
@NoArgsConstructor
@AllArgsConstructor
public class HotelDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    @Type(type = "text")
    private String description;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "hotelDetails",cascade = CascadeType.ALL)
    private List<Hotel> hotels;

    @Override
    public String toString() {
        return "Hotel detail [id=" + id
                + ", name=" + name
                + ", description=" + description
                + "]";
    }
}
