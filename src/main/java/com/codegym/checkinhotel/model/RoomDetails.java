package com.codegym.checkinhotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@Table(name = "room_details")
@NoArgsConstructor
@AllArgsConstructor
public class RoomDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "quantity_exists")
    @Min(0)
    private int quantityExists;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "roomDetails",cascade = CascadeType.ALL)
    private List<Room>rooms;
}
