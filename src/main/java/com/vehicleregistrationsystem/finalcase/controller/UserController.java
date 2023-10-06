package com.vehicleregistrationsystem.finalcase.controller;

import com.vehicleregistrationsystem.finalcase.requests.UserRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import com.vehicleregistrationsystem.finalcase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDTO) {
        return userService.updateUser(userId, userRequestDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
