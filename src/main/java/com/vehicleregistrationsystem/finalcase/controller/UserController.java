package com.vehicleregistrationsystem.finalcase.controller;

import com.vehicleregistrationsystem.finalcase.exception.GlobalExceptionHandler;
import com.vehicleregistrationsystem.finalcase.requests.UserRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import com.vehicleregistrationsystem.finalcase.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Valid
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        final List<UserResponseDto> userResponseDto = userService.getAllUsers();
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        final UserResponseDto userResponseDto = userService.getUserById(userId);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDTO) {
        final UserResponseDto userResponseDto = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDto userRequestDTO) {
        final UserResponseDto userResponseDto = userService.updateUser(userId, userRequestDTO);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}
