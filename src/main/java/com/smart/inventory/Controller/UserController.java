package com.smart.inventory.Controller;

import com.smart.inventory.DTO.UserDTO;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Mapper.UserMapper;
import com.smart.inventory.Service.UserService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper; // Convert user to userDto.


    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // Register a new user.
    @PostMapping("/register")
    public Result register(@RequestBody User newUser) {
        User savedUser = this.userService.save(newUser);
        UserDTO savedUserDto = this.userMapper.convertToDto(savedUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedUserDto);
    }

    // Get all users.
    @GetMapping
    public Result findAllUsers() {
        List<User> foundUser = this.userService.findAll();


        List<UserDTO> userDtos = foundUser.stream() // Convert the list to a stream.
                .map(this.userMapper::convertToDto) // Convert each item to UserDto.
                .collect(Collectors.toList()); // Collect the stream to a list.

        // Note that UserDto does not contain password field.
        return new Result(true, StatusCode.SUCCESS, "Find All Success", userDtos);
    }

    // Get a user by id.
    @GetMapping("/{userId}")
    public Result findUserById(@PathVariable Long userId) {
        User foundUser = this.userService.findById(userId);
        UserDTO userDto = this.userMapper.convertToDto(foundUser);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", userDto);
    }

    // We are not using this to update password, need another changePassword method in this class.
    @PutMapping("/update/{userId}")
    public Result updateUser(@PathVariable Long userId, @RequestBody UserDTO userDto) {
        User update = this.userMapper.convertToEntity(userDto);
        User updatedUser = this.userService.update(userId, update);
        UserDTO updatedUserDto = this.userMapper.convertToDto(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "Update Success", updatedUserDto);
    }

    // Change password.
    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Long userId) {
        this.userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "Delete Success");
    }

}