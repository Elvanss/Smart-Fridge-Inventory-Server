package com.smart.inventory.Controller;

import com.smart.inventory.DTO.MealDTO;
import com.smart.inventory.Entity.Meal;
import com.smart.inventory.Mapper.MealMapper;
import com.smart.inventory.Service.FileStorageService;
import com.smart.inventory.Service.MealService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/meals")
public class MealController {

    private final MealService mealService;
    private final MealMapper mealMapper;
    private final FileStorageService fileStorageService;

    public MealController(MealService mealService, MealMapper mealMapper, FileStorageService fileStorageService) {
        this.mealService = mealService;
        this.mealMapper = mealMapper;
        this.fileStorageService = fileStorageService;
    }

    // Get all meals
    @GetMapping
    public Result getMeals() {
        List<Meal> meals = this.mealService.findAll();
        List<MealDTO> mealDTOS = meals.stream()
            .map(mealMapper::convertToDto)
            .toList();

        return new Result(true, StatusCode.SUCCESS, "All Meals", meals);
    }

    // Find a meal by id
    @GetMapping("/{id}")
    public Result getMealById(@PathVariable Long id) {
        Meal meal = this.mealService.findById(id);
        MealDTO mealDTO = this.mealMapper.convertToDto(meal);
        return new Result(true, StatusCode.SUCCESS, "Meal Found", mealDTO);
    }

    // Add a meal
    @PostMapping("/add")
    public Result addMeal(@RequestParam("file") MultipartFile file, @RequestBody MealDTO mealDTO) throws IOException {
        String imageUrl = fileStorageService.storeFile(file); // Store the file and get the URL
        Meal meal = this.mealMapper.convertToEntity(mealDTO);
        Meal newMeal = this.mealService.save(meal);
        newMeal.setMealImageUrl(imageUrl);
        MealDTO newMealDTO = this.mealMapper.convertToDto(newMeal);
        return new Result(true, StatusCode.SUCCESS, "Meal Added", newMealDTO);
    }

    // Update a meal
    @PutMapping("/update/{id}")
    public Result updateMeal(@PathVariable Long id, @RequestBody MealDTO mealDTO) {
        Meal meal = this.mealMapper.convertToEntity(mealDTO);
        Meal updatedMeal = this.mealService.update(id, meal);
        MealDTO updatedMealDTO = this.mealMapper.convertToDto(updatedMeal);
        return new Result(true, StatusCode.SUCCESS, "Meal Updated", updatedMealDTO);
    }

    // Delete a meal
    @DeleteMapping("/delete/{id}")
    public Result deleteMeal(@PathVariable Long id) {
        this.mealService.delete(id);
        return new Result(true, StatusCode.SUCCESS, "Meal Deleted");
    }

    // Get meal suggestion
    @GetMapping("/suggestion")
    public Result getMealSuggestion(@RequestParam List<String> items, @RequestParam("days") Integer days, @RequestParam("maxMealPerDays") Integer maxMealPerDays) {
        List<Meal> meals = this.mealService.getMealSuggestion(items, days, maxMealPerDays);
        List<MealDTO> mealDTOS = meals.stream()
            .map(mealMapper::convertToDto)
            .toList();

        return new Result(true, StatusCode.SUCCESS, "Suggested Meals", mealDTOS);
    }
}
