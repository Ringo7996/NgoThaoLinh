package com.example.ngothaolinh.controller.restcontroller;


import com.example.ngothaolinh.model.entity.Pet;
import com.example.ngothaolinh.model.entity.User;
import com.example.ngothaolinh.model.roombooking.RoomBooking;
import com.example.ngothaolinh.security.AuthenticationFacade;
import com.example.ngothaolinh.service.PetService;
import com.example.ngothaolinh.service.RoomBookingService;
import com.example.ngothaolinh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
//@PreAuthorize("hasAnyRole('ROLE_ROOT_ADMIN', 'ROLE_HOTEL_STAFF', 'ROLE_HOTEL_ADMIN')")
public class UserController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private RoomBookingService roomBookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @GetMapping("/principal")
    public User getUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return userService.findByEmail((String) authentication.getPrincipal());
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        System.out.println("chạy vào trong");
        System.out.println(getUser());

//        return userService.getAllUsersWithPage(pageable);
        return ResponseEntity.ok(getUser());
    }


    //Xem info user
    @GetMapping("/{userId}")
    public User getAnUser(@PathVariable(name = "userId") Integer userId) {
        return userService.getAnUser(userId);
    }

    // Xem list pet của user
    @GetMapping("/{userId}/pets")
    public List<Pet> getMyPets() {
        return getUser().getPets();
    }

    //Xem từng pet: xem được của bản thân những con isVisibale
    @GetMapping("/{userId}/pets/{petId}")
    public Pet getOnePet(@PathVariable(name = "userId") Integer userId, @PathVariable(name = "petId") Integer petId) {
        return petService.getOnePet(petId, getUser());
    }

    // xem list booking
    @GetMapping("/{userId}/room-bookings")
    public List<RoomBooking> getMyRoomBookings(@PathVariable(name = "userId") Integer userId) {
        return roomBookingService.findMyBookings(getUser());
    }


    @GetMapping("/{userId}/room-bookings/{roomBookingId}")
    public RoomBooking getOneRoomBooking(@PathVariable(name = "userId") Integer userId, @PathVariable(name = "roomBookingId") Integer roomBookingId) {
        return roomBookingService.findByIdWithUser(roomBookingId, getUser());
    }

}
