package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.UserDTO;
import com.smart.inventory.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO convertToDto(User source) {
        return new UserDTO(source.getId(),
                source.getUsername(),
                source.getEmail(),
                source.getPassword(),
                source.getType(),
                source.getNumberOfProfiles(),
                source.getNumberOfShoppingLists());
    }

    public User convertToEntity(UserDTO source) {
        User user = new User();
        user.setUsername(source.getUsername());
        user.setEmail(source.getEmail());
        user.setPassword(source.getPassword());
        user.setType(source.getRoles());
        return user;
    }

    
}
