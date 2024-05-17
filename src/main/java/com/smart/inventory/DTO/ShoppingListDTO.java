package com.smart.inventory.DTO;

import com.smart.inventory.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShoppingListDTO {
    private Long id;
    private String name;
    private List<String> items = new ArrayList<>();
    private Double cost;
    private Integer estimatedDate;
    private String description;
    private Integer numberOfUser;
}
