package com.smart.inventory.Repository;

import com.smart.inventory.Entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    @Query("SELECT m FROM Meal m WHERE m.name LIKE %?1%")
    List<Meal> findByNameContaining(String name);
}
