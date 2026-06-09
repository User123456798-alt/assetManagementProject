package com.example.assetmanagment.service;

import com.example.assetmanagment.dto.UserResponseDto;
import com.example.assetmanagment.entity.UserEntity;
import com.example.assetmanagment.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponseDto> findAllUsers(Pageable page) {
    logger.info("entered find all users");
       return userRepository.findAll(page)
                .map(this::toDto);
    }



    private UserResponseDto toDto(UserEntity entity) {
        return new UserResponseDto(
                entity.getUserId(),
                entity.getUserFirstName(),
                entity.getUserLastName());
    }
}
