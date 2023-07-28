package com.example.ngothaolinh.service;


import com.example.ngothaolinh.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    void sendResetPwEmail(String email);

    void resetPw(String email, String encodedPassword);

    User findByEmail(String name);

    Page<User> getAllUsersWithPage(Pageable pageable);

    User getAnUser(Integer userId);
}
