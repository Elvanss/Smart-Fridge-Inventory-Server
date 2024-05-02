package com.smart.inventory.DTO;

import com.smart.inventory.Entity.Type.RoleList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

public record UserDTO(Long id,
                      String username,
                      String email,
                      String password,
                      RoleList roles) {
}
