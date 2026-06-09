package com.example.assetmanagment.controller;

import com.example.assetmanagment.dto.UserResponseDto;
import com.example.assetmanagment.service.UserService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Slice<UserResponseDto> getAllUsers(Pageable page) {
        return userService.findAllUsers(page);
    }
}
