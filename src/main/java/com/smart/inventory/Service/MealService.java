package com.smart.inventory.Service;

import com.smart.inventory.Entity.Meal;
import com.smart.inventory.Repository.MealRepository;
import com.smart.inventory.System.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> findAll() {
        return this.mealRepository.findAll();
    }

    public Meal findById(Long mealId) {
        return this.mealRepository.findById(mealId)
                .orElseThrow(() -> new ObjectNotFoundException("meal not found!", mealId));
    }

    public Meal save(Meal meal) {
        meal.setName(meal.getName());
        meal.setMealImageUrl(meal.getMealImageUrl());
        meal.setCookingTime(meal.getCookingTime());
        meal.setIngredients(meal.getIngredients());
        meal.setDietaryLists(meal.getDietaryLists());
        meal.setCookingMethod(meal.getCookingMethod());
        return this.mealRepository.save(meal);
    }

    public Meal update(Long mealId, Meal update) {
        Meal oldMeal = this.mealRepository.findById(mealId)
                .orElseThrow(() -> new ObjectNotFoundException("meal not found!", mealId));
        oldMeal.setName(update.getName());
        oldMeal.setMealImageUrl(update.getMealImageUrl());
        oldMeal.setCookingTime(update.getCookingTime());
        oldMeal.setIngredients(update.getIngredients());
        oldMeal.setDietaryLists(update.getDietaryLists());
        oldMeal.setCookingMethod(update.getCookingMethod());
        return this.mealRepository.save(oldMeal);
    }

    public void delete(Long mealId) {
        this.mealRepository.findById(mealId)
                .orElseThrow(() -> new ObjectNotFoundException("meal not found!", mealId));
        this.mealRepository.deleteById(mealId);
    }

//    public List<Meal> getMealSuggestion(List<String> items, int days) {
//        List<Meal> allMeals = this.mealRepository.findAll();
//
//        List<Meal> suitableMeals = allMeals.stream()
//            .filter(meal -> meal.getIngredients().stream().anyMatch(items::contains))
//            .collect(Collectors.toList());
//
//        if (suitableMeals.isEmpty()) {
//            throw new RuntimeException("No suitable meals found");
//        }
//
//        Random rand = new Random();
//        List<Meal> selectedMeals = new ArrayList<>();
//        // modify that if could be more or less than 3 meals a day
//
//        int mealsNeeded = days * 3; // 3 meals per day
//
//
//        for (int i = 0; i < mealsNeeded; i++) {
//            Meal randomMeal = suitableMeals.get(rand.nextInt(suitableMeals.size()));
//            selectedMeals.add(randomMeal);
//            suitableMeals.remove(randomMeal);
//        }
//
//        return selectedMeals;
//    }
    public List<Meal> getMealSuggestion(List<String> items, int days, int maxMealsPerDay) {
        List<Meal> allMeals = this.mealRepository.findAll();

        List<Meal> suitableMeals = allMeals.stream()
            .filter(meal -> meal.getIngredients().stream().anyMatch(items::contains))
            .collect(Collectors.toList());

        if (suitableMeals.isEmpty()) {
            throw new RuntimeException("No suitable meals found");
        }

        Random rand = new Random();
        List<Meal> selectedMeals = new ArrayList<>();

        int mealsNeeded = Math.min(days * maxMealsPerDay, suitableMeals.size());

        for (int i = 0; i < mealsNeeded; i++) {
            Meal randomMeal = suitableMeals.get(rand.nextInt(suitableMeals.size()));
            selectedMeals.add(randomMeal);
            suitableMeals.remove(randomMeal);
        }

        return selectedMeals;
    }
}
