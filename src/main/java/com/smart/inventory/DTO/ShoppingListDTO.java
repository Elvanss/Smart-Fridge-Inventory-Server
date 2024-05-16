package com.smart.inventory.DTO;

import com.smart.inventory.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShoppingListDTO {
    private Long id;
    private String name;
    private Map<String, Integer> items = new HashMap<>();
    private String description;
    private UserDTO user;
}
