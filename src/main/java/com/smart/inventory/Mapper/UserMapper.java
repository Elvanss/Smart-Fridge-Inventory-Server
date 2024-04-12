package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.UserDTO;
import com.smart.inventory.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO convertToDto(User source) {
        // We should not setting password in DTO.
        return new UserDTO(source.getId(),
                source.getUsername(),
                source.getEmail(),
                source.getPassword(),
                source.getRoleUsers());
    }

    public User convertToEntity(UserDTO source) {
        User user = new User();
        user.setUsername(source.username());
        user.setEmail(source.email());
        user.setPassword(source.password());
        user.setRoleUsers(source.roles());
        return user;
    }
}
