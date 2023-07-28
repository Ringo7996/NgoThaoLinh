package com.example.ngothaolinh.service;


import com.example.ngothaolinh.model.entity.User;
import com.example.ngothaolinh.model.request.CreateRoomBookingRequest;
import com.example.ngothaolinh.model.roombooking.RoomBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomBookingService {
    List<RoomBooking> findMyBookings(User user);

    RoomBooking findByIdWithUser(Integer roomBookingId, User user);

    Page<RoomBooking> getAllRoomBookingsWithPage(Pageable pageable);

    RoomBooking makeARoomBooking(CreateRoomBookingRequest request, User user);
}
