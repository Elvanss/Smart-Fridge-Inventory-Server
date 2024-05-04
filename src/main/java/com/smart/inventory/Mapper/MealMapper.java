package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.MealDTO;
import com.smart.inventory.Entity.Meal;
import org.springframework.stereotype.Component;

@Component
public class MealMapper {

    public MealDTO convertToDto (Meal source) {
        return new MealDTO(source.getId(),
                source.getName(),
                source.getMealImageUrl(),
                source.getCookingTime(),
                source.getIngredients(),
                source.getDietaryLists(),
                source.getCookingMethod());
    }

    public Meal convertToEntity(MealDTO source) {
        Meal meal = new Meal();
        meal.setName(source.getName());
        meal.setMealImageUrl(source.getMealImageUrl());
        meal.setCookingTime(source.getCookingTime());
        meal.setIngredients(source.getIngredients());
        meal.setDietaryLists(source.getDietaryLists());
        meal.setCookingMethod(source.getCookingMethod());
        return meal;
    }
}
