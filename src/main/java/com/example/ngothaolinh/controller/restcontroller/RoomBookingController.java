package com.example.ngothaolinh.controller.restcontroller;

import com.example.ngothaolinh.model.entity.User;
import com.example.ngothaolinh.model.request.CreateRoomBookingRequest;
import com.example.ngothaolinh.model.roombooking.RoomBooking;
import com.example.ngothaolinh.security.AuthenticationFacade;
import com.example.ngothaolinh.service.PetService;
import com.example.ngothaolinh.service.RoomBookingService;
import com.example.ngothaolinh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/room-bookings")
@PreAuthorize("hasAnyRole('ROLE_ROOT_ADMIN', 'ROLE_HOTEL_STAFF', 'ROLE_HOTEL_ADMIN')")
public class RoomBookingController {
    @Autowired
    private RoomBookingService roomBookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private AuthenticationFacade authenticationFacade;


    public User getUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return userService.findByEmail((String) authentication.getPrincipal());
    }

    @GetMapping("/")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<RoomBooking> getAllRoomBooking(Pageable pageable) {
        return roomBookingService.getAllRoomBookingsWithPage(pageable);
    }

    @PatchMapping("/make-booking")
    public RoomBooking makeARoomBooking(@RequestParam CreateRoomBookingRequest request) {
        Integer userId = getUser().getId();

        return roomBookingService.makeARoomBooking(request, getUser());
    }
}
