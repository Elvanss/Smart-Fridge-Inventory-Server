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
                source.getType());
    }

//    public UserDTO convertToDtoForLogin(User source) {
//        // We should not set password in DTO.
//        return new UserDTO(source.getId(),
//                source.getUsername(),
//                source.getEmail(),
//                source.getPassword(),
//                source.getType());
//    }

    public User convertToEntity(UserDTO source) {
        User user = new User();
        user.setUsername(source.getUsername());
        user.setEmail(source.getEmail());
//        user.setPassword(source.getPassword());
        user.setType(source.getRoles());
        return user;
    }

    
}
