package com.smart.inventory.Service;

import com.smart.inventory.Entity.NutritionTarget;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Repository.NutritionTargetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionTargetService {

    private final NutritionTargetRepository nutritionTargetRepository;

    public NutritionTargetService(NutritionTargetRepository nutritionTargetRepository) {
        this.nutritionTargetRepository = nutritionTargetRepository;
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
}
