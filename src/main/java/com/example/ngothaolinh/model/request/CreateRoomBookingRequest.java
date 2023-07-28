package com.example.ngothaolinh.model.request;


import com.example.ngothaolinh.model.entity.PaymentType;
import com.example.ngothaolinh.model.entity.Pet;
import com.example.ngothaolinh.model.roombooking.HotelRoomType;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomBookingRequest {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Pet pet;

    @NotNull
    private HotelRoomType hotelRoomType;

    @NotNull
    private PaymentType paymentType;


}
