package com.smart.inventory.DTO;

import com.smart.inventory.Entity.Type.DietaryList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfileDTO {
    private Long id;
    private String name;
    private Integer age;
    private DietaryList dietary;
    private String Allergies;
    private String description;
    private UserDTO user;
    private int numberOfConsumptionRecords;
    private NutritionTargetDTO nutritionTarget;
}
