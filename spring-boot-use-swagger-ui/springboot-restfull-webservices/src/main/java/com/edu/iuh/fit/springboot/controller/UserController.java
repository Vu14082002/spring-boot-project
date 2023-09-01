package com.edu.iuh.fit.springboot.controller;


import com.edu.iuh.fit.springboot.dto.UserDTO;
import com.edu.iuh.fit.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(
        name = "CRUD for USER",
        description = "CRUD anf find byID"
)
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    // http://localhost:8080/api/users
    @Operation(
            summary = "Create User REST API",
            description = "xxxxxxxx"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATE"
    )
    @PostMapping
    private ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDto) {
//        c1
        UserDTO savedUser = userService.createUser(userDto);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    // http://localhost:8080/api/users/1
    @Operation(
            summary = "find User by ID REST API",
            description = "xxxxxxxx"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS SUCCESS"
    )
    @GetMapping("{id}")
    private ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    // http://localhost:8080/api/users

    @Operation(
            summary = "FIND ALL  User  REST API",
            description = "xxxxxxxx"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS SUCCESS"
    )
    @GetMapping
    private ResponseEntity<List<UserDTO>> findAllUser() {
        List<UserDTO> userDTOList = userService.getAllUser();
        return ResponseEntity.ok(userDTOList);
    }

    @Operation(
            summary = "UPDATE User BY ID REST API",
            description = "xxxxxxxx"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDto, @PathVariable Long id) {
        userDto.setId(id);
        userService.updateUser(userDto);
        return ResponseEntity.ok(userDto);
    }
    @Operation(
            summary = "DELETE User by ID REST API",
            description = "xxxxxxxx"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteuserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("Delete success user " + id);
    }


//    @ExceptionHandler(ResultNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResultNotFoundException exception,
//                                                                        WebRequest webRequest
//    ) {
//        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false), "USER_NOT_FOUND");
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
