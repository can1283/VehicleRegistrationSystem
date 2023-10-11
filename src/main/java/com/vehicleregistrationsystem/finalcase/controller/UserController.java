package com.vehicleregistrationsystem.finalcase.controller;

import com.vehicleregistrationsystem.finalcase.requests.UserRequestDto;
import com.vehicleregistrationsystem.finalcase.responses.UserResponseDto;
import com.vehicleregistrationsystem.finalcase.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/pageable")
    public ResponseEntity<List<UserResponseDto>> slice(int pageNo, int pageSize) {
        List<UserResponseDto> slice = userService.slice(pageNo, pageSize);
        return ResponseEntity.ok(slice);
    }

    @GetMapping("/sorted-by-username")
    public ResponseEntity<List<UserResponseDto>> getAllUsersSortedByUserName(@RequestParam(required = false, defaultValue = "ASC") String direction) {
        Sort.Direction sort = "DESC".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final List<UserResponseDto> userResponseDtoList = userService.getAllUsersSortedByUserName(sort);
        return ResponseEntity.ok(userResponseDtoList);
    }

    @GetMapping("/sorted-by-creation-date")
    public ResponseEntity<List<UserResponseDto>> getAllUsersSortedByCreationDate(@RequestParam(required = false, defaultValue = "ASC") String direction) {
        Sort.Direction sort = "DESC".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final List<UserResponseDto> userResponseDtoList = userService.getAllUsersSortedByCreationDate(sort);
        return ResponseEntity.ok(userResponseDtoList);
    }

}
