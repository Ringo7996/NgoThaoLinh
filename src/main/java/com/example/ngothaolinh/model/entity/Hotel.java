package com.example.ngothaolinh.model.entity;


import com.example.ngothaolinh.model.roombooking.HotelRoomType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String disctrict;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<HotelRoomType> hotelRoomTypes = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "myHotels", fetch = FetchType.EAGER)
    private List<User> staff = new ArrayList<>();

}