package com.smart.inventory.Repository;

import com.smart.inventory.Entity.NutritionTarget;
import com.smart.inventory.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NutritionTargetRepository extends JpaRepository<NutritionTarget, Long> {

    @Query("SELECT n FROM NutritionTarget n WHERE n.profile = ?1")
    NutritionTarget findByProfile(Profile profile);
}
