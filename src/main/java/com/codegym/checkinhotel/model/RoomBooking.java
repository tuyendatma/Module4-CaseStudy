package com.codegym.checkinhotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "room_booking")
@NoArgsConstructor
@AllArgsConstructor
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "booking_date")
    @CreationTimestamp
    private Timestamp bookingDate;
    @Column(name = "checkin_date", nullable = false)
    private String checkinDate;
    @Column(name = "checkout_date", nullable = false)
    private String checkoutDate;
    @Column
    @Type(type = "text")
    private String note;
    @Column(name = "quantity_booking")
    @Min(1)
    private int quantityBooking;

//    @ManyToOne
//    @JoinColumn(name = "room_id",nullable = false)
//    private Room room;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "room_booking_room",
            joinColumns = {@JoinColumn(name = "room_booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private List<Room> rooms;

    @Override
    public String toString() {
        return "Room booking [id=" + id
                + ", booking date=" + bookingDate
                + ", checkin date=" + checkinDate
                + ", checkout date=" + checkoutDate
                + ", note=" + note
                +", quantity booking" + quantityBooking
                + "]";
    }
}
