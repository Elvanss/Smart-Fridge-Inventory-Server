package com.smart.inventory.Service;

import com.smart.inventory.Entity.NutritionTarget;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Repository.NutritionTargetRepository;
import com.smart.inventory.Repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NutritionTargetService {

    private final NutritionTargetRepository nutritionTargetRepository;
    private final ProfileRepository profileRepository;

    public NutritionTargetService(NutritionTargetRepository nutritionTargetRepository,
                                  ProfileRepository profileRepository) {
        this.nutritionTargetRepository = nutritionTargetRepository;
        this.profileRepository = profileRepository;
    }

    public NutritionTarget saveNutritionTarget(NutritionTarget nutritionTarget) {
        return nutritionTargetRepository.save(nutritionTarget);
    }

    public List<NutritionTarget> getAllNutritionTargets() {
        return nutritionTargetRepository.findAll();
    }

    public NutritionTarget getNutritionTargetByProfile(Profile profile) {
        return nutritionTargetRepository.findByProfile(profile);
    }

    public void setNutritionTarget (Long profileId, Integer targetCalories, Integer targetProtein, Integer targetFat) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found!"));

        NutritionTarget nutritionTarget = nutritionTargetRepository.findByProfile(profile);

        // Created the new object while it doesn't create yet
        if (nutritionTarget == null) {
            nutritionTarget = new NutritionTarget();
        }

        nutritionTarget.setProfile(profile);
        nutritionTarget.setTargetCalories(targetCalories);
        nutritionTarget.setTargetProtein(targetProtein);
        nutritionTarget.setTargetFat(targetFat);

        profile.setNutritionTarget(targetCalories, targetProtein, targetFat);
        nutritionTargetRepository.save(nutritionTarget);
        profileRepository.save(profile);

    }

    // Reset the nutrition target
    public void resetNutritionTarget(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found!"));

        NutritionTarget nutritionTarget = nutritionTargetRepository.findByProfile(profile);

        if (nutritionTarget != null) {
            nutritionTargetRepository.delete(nutritionTarget);
        }

        profile.setNutritionTarget(0, 0, 0);
        profileRepository.save(profile);
    }

    //Show the progress against the target
    public Map<String, Double> getProgressAgainstTargets(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found!"));
        return profile.getProgressAgainstTargets();
    }
}
