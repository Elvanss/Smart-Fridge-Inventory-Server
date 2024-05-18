package com.smart.inventory.Controller;

import com.smart.inventory.DTO.UserDTO;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Mapper.UserMapper;
import com.smart.inventory.Service.UserService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    // Admin Side: Get all users.
    @GetMapping
    public Result getUsers() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOS = users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "All Users", userDTOS);
    }

    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable Long userId) {
        User user = userService.findById(userId);
        UserDTO userDTO = userMapper.convertToDto(user);
        return new Result(true, StatusCode.SUCCESS, "User Found", userDTO);
    }

    // Sequence Diagram 2
    // Register a new user.
    @PostMapping("/register")
    public Result registerUser(@RequestBody UserDTO userDTO) {
        User newUser = this.userMapper.convertToEntity(userDTO);
        User savedUser = this.userService.save(newUser);
        UserDTO savedUserDto = this.userMapper.convertToDto(savedUser);
        return new Result(true, StatusCode.SUCCESS, "Add Success", savedUserDto);
    }

    // Requirement 1: Update a user.
    @PutMapping("/update/{userId}")
    public Result updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        User updatedUser = userService.update(userId, user);
        UserDTO updatedUserDTO = userMapper.convertToDto(updatedUser);
        return new Result(true, StatusCode.SUCCESS, "User Updated", updatedUserDTO);
    }

    @DeleteMapping("/delete/{userId}")
    public Result deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return new Result(true, StatusCode.SUCCESS, "User Deleted");
    }

    // Requirement 1: Assign a profile to a user.
    @PostMapping("/assignProfile/{userId}/{profileId}")
    public Result assignProfileToUser(@PathVariable Long userId, @PathVariable Long profileId) {
        userService.assignProfileToUser(userId, profileId);
        return new Result(true, StatusCode.SUCCESS, "Profile Assigned to User");
    }
}