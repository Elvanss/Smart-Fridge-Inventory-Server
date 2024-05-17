package com.smart.inventory.Controller;

import com.smart.inventory.Service.NutritionTargetService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/nutrition-targets")
public class NutritionTargetController {

    private final NutritionTargetService nutritionTargetService;

    public NutritionTargetController(NutritionTargetService nutritionTargetService) {
        this.nutritionTargetService = nutritionTargetService;
    }

    @PostMapping("/set/{profileId}")
    public Result setNutritionTarget(@PathVariable Long profileId,
                                     @RequestParam("calo") Integer targetCalories,
                                     @RequestParam("protein") Integer targetProtein,
                                     @RequestParam("fat") Integer targetFat) {
        nutritionTargetService.setNutritionTarget(profileId, targetCalories, targetProtein, targetFat);
        return new Result(true, StatusCode.SUCCESS, "Nutrition target set successfully", null);
    }

    @PostMapping("/reset/{profileId}")
    public Result resetNutritionTarget(@PathVariable Long profileId) {
        nutritionTargetService.resetNutritionTarget(profileId);
        return new Result(true, StatusCode.SUCCESS, "Nutrition target reset successfully", null);
    }

    @GetMapping("/progress/{profileId}")
    public Result getProgressAgainstTargets(@PathVariable long profileId) {
        Map<String, Double> progress = nutritionTargetService.getProgressAgainstTargets(profileId);
        return new Result(true, StatusCode.SUCCESS, "Progress against targets", progress);
    }
}
