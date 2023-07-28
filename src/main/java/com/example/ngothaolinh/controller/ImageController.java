package com.example.ngothaolinh.controller;


import com.example.ngothaolinh.model.entity.Image;
import com.example.ngothaolinh.model.entity.User;
import com.example.ngothaolinh.security.AuthenticationFacade;
import com.example.ngothaolinh.service.ImageService;
import com.example.ngothaolinh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private UserService userService;


    public User getUser() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return userService.findByEmail((String) authentication.getPrincipal());
    }

    // upload ảnh
    @PostMapping("/files")
    public Image uploadImage(@ModelAttribute("file") MultipartFile file) {
        // upload len user id 1;
        return imageService.uploadImageByUserId(1, file);
    }


    // xem ảnh
    @GetMapping("/files/{id}")
    public ResponseEntity<?> readImage(@PathVariable Integer id) {
        Image image = imageService.readImageById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData());
    }


    @GetMapping("/users/{id}/files")
    public List<Image> getImageListByUserId(@PathVariable Integer id) {
        return imageService.getImageListByUserId(id);

    }


    // 3. xoá ảnh
    @DeleteMapping("/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable(name = "id") Integer Fileid) {
        // đang đăng nhập user id 1;
        imageService.deleteImageById(Fileid, 1);
        return ResponseEntity.noContent().build();
    }

}