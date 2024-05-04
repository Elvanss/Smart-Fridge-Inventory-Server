package com.smart.inventory.DTO;

import com.smart.inventory.Entity.Type.DietaryList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {
    private Long id;
    private String name;
    private String mealImageUrl;
    private String cookingTime;
    private List<String> ingredients = new ArrayList<>();
    private List<DietaryList> dietaryLists = new ArrayList<>();
    private String cookingMethod;
}
