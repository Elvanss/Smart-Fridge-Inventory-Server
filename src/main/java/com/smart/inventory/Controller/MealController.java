package com.smart.inventory.Controller;

import com.smart.inventory.DTO.MealDTO;
import com.smart.inventory.Entity.Meal;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Mapper.MealMapper;
import com.smart.inventory.Service.FileStorageService;
import com.smart.inventory.Service.MealService;
import com.smart.inventory.Service.ProfileService;
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
    private final ProfileService profileService;

    public MealController(MealService mealService,
                          MealMapper mealMapper,
                          FileStorageService fileStorageService, ProfileService profileService) {
        this.mealService = mealService;
        this.mealMapper = mealMapper;
        this.fileStorageService = fileStorageService;
        this.profileService = profileService;
    }

    // Sequence Diagram 12
    // Requirement 3: Get all meals
    @GetMapping
    public Result getMeals() {
        List<Meal> meals = this.mealService.findAll();
        List<MealDTO> mealDTOS = meals.stream()
            .map(mealMapper::convertToDto)
            .toList();

        return new Result(true, StatusCode.SUCCESS, "All Meals", meals);
    }

    // Requirement 3: Find a meal by id
    @GetMapping("/{id}")
    public Result getMealById(@PathVariable Long id) {
        Meal meal = this.mealService.findById(id);
        MealDTO mealDTO = this.mealMapper.convertToDto(meal);
        return new Result(true, StatusCode.SUCCESS, "Meal Found", mealDTO);
    }

    // Sequence Diagram 13
    // Requirement 3: Search meal by name
    @GetMapping("/search")
    public Result searchMeal(@RequestParam("name") String name) {
        List<Meal> meals = this.mealService.searchingMeal(name);
        List<MealDTO> mealDTOS = meals.stream()
            .map(mealMapper::convertToDto)
            .toList();

        return new Result(true, StatusCode.SUCCESS, "Meals Found", mealDTOS);
    }

    //Requirement 3: Add a meal
    @PostMapping("/add")
    public Result addMeal(@RequestParam("file") MultipartFile file, @RequestBody MealDTO mealDTO) throws IOException {
        String imageUrl = fileStorageService.storeFile(file); // Store the file and get the URL
        Meal meal = this.mealMapper.convertToEntity(mealDTO);
        Meal newMeal = this.mealService.save(meal);
        newMeal.setMealImageUrl(imageUrl);
        MealDTO newMealDTO = this.mealMapper.convertToDto(newMeal);
        return new Result(true, StatusCode.SUCCESS, "Meal Added", newMealDTO);
    }

    // Admin: Update a meal
    @PutMapping("/update/{id}")
    public Result updateMeal(@PathVariable Long id, @RequestBody MealDTO mealDTO) {
        Meal meal = this.mealMapper.convertToEntity(mealDTO);
        Meal updatedMeal = this.mealService.update(id, meal);
        MealDTO updatedMealDTO = this.mealMapper.convertToDto(updatedMeal);
        return new Result(true, StatusCode.SUCCESS, "Meal Updated", updatedMealDTO);
    }

    // Admin: Delete a meal
    @DeleteMapping("/delete/{id}")
    public Result deleteMeal(@PathVariable Long id) {
        this.mealService.delete(id);
        return new Result(true, StatusCode.SUCCESS, "Meal Deleted");
    }

    // Sequence Diagram 14
    // Requirement 3: Get meal suggestion
    @GetMapping("/suggestion")
    public Result getMealSuggestion(@RequestParam List<String> items,
                                    @RequestParam("days") Integer days,
                                    @RequestParam("maxMealPerDays") Integer maxMealPerDays) {
        List<Meal> meals = this.mealService.getMealSuggestion(items, days, maxMealPerDays);
        List<MealDTO> mealDTOS = meals.stream()
            .map(mealMapper::convertToDto)
            .toList();

        return new Result(true, StatusCode.SUCCESS, "Suggested Meals", mealDTOS);
    }

    // Sequence Diagram 15
    // Requirement 3: Add meal to favorite
    @PostMapping("/add-to-favorite")
    public Result addMealToFavorite(@RequestParam("mealId") Long mealId,
                                    @RequestParam("profileId") Long id) {
       this.mealService.assignedMealToProfile(mealId, id);
       return new Result(true, StatusCode.SUCCESS, "Meal Added to Favorite");
    }

    // Sequence Diagram 16
    @GetMapping("/favorite")
    public Result getFavoriteMeals(@RequestParam("userId") Long userId, @RequestParam("profileId") Long profileId) {
        Profile profile = profileService.getProfileByUserId(userId, profileId);
        List<Meal> meals = this.mealService.getMealsByProfile(profile);
        List<MealDTO> mealDTOS = meals.stream()
            .map(mealMapper::convertToDto)
            .toList();

        return new Result(true, StatusCode.SUCCESS, "Favorite Meals", mealDTOS);
    }

    @DeleteMapping("/delete-from-favorite")
    public Result deleteMealFromFavorite(@RequestParam("mealId") Long mealId, @RequestParam("profileId") Long profileId) {
        this.mealService.deleteMealfromProfileFavorite(mealId, profileId);
        return new Result(true, StatusCode.SUCCESS, "Meal Deleted from Favorite");
    }

}
