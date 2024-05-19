package com.smart.inventory.Entity;

import com.smart.inventory.Entity.Type.DietaryList;
import io.swagger.models.auth.In;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Profile.java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "dietary", nullable = false)
    private DietaryList dietary;

    @Column(name = "allergies", nullable = false)
    private String Allergies;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private FridgeInventory fridgeInventory;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "profiles", cascade = CascadeType.ALL)
    private List<Meal> mealSaved = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsumptionRecord> consumptionRecords = new ArrayList<>();

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private NutritionTarget nutritionTarget;

    /*Meal list Actions*/
    public void addMealSaved(Meal meal) {
        meal.getProfiles().add(this);
        mealSaved.add(meal);
    }

    public void removeMealSaved(Meal meal) {
        meal.getProfiles().remove(this);
        mealSaved.remove(meal);
    }

    public void removeAllMealSaved() {
        mealSaved.forEach(meal -> meal.getProfiles().remove(this));
        mealSaved = new ArrayList<>();
    }

    public Integer numberOfSavedMeal() {
        return this.mealSaved.size();
    }

// Requirement 2: Add an item to the fridge inventory
    public void addItemToFridgeInventory(Item item) {
        if (fridgeInventory == null) {
            User user = this.getUser();
            SharedFridge sharedFridge = user.getSharedFridge();
            fridgeInventory = new FridgeInventory();
            fridgeInventory.setSharedFridge(sharedFridge);
            fridgeInventory.setProfile(this);
            sharedFridge.addFridgeInventory(fridgeInventory);
        }
        fridgeInventory.addItem(item);
    }

    // Requirement 4: Transfer the items in consumption record(Requirement 2)
    public void transferItemToConsumptionRecord(Item item, Integer numberOfItems) {
        ConsumptionRecord consumptionRecord = new ConsumptionRecord();
        item.setStock(item.getStock() - numberOfItems); // Update the stock of the item
        consumptionRecord.setItem(item); // Set the item in the consumption record
        consumptionRecord.setQuantity(numberOfItems); // Set the number of items in the consumption record
        consumptionRecord.setProfile(this); // Set the profile in the consumption record
        consumptionRecords.add(consumptionRecord);
    }


    // Set the nutrition target for the profile (Requirement 6)
    public void setNutritionTarget(Integer targetCalories, Integer targetProtein, Integer targetFat) {
        if (nutritionTarget == null) {
            nutritionTarget = new NutritionTarget();
            nutritionTarget.setProfile(this);
        }
        nutritionTarget.setTargetCalories(targetCalories);
        nutritionTarget.setTargetProtein(targetProtein);
        nutritionTarget.setTargetFat(targetFat);
    }

    // Tracking the nutrition target for the profile (Requirement 6)
    public Map<String, Double> getProgressAgainstTargets() {
        Map<String, Double> progress = new HashMap<>();
        NutritionTarget nutritionTarget = getNutritionTarget();

        if (nutritionTarget != null) {
            Integer targetCalories = nutritionTarget.getTargetCalories();
            Integer targetProtein = nutritionTarget.getTargetProtein();

            // Calculate progress for calories
            double consumedCalories = consumptionRecords.stream()
                    .mapToDouble(cr -> cr.getQuantity() * cr.getItem().getCalories()).sum();
            progress.put("calories", (double) consumedCalories * 100 / targetCalories);

            // Calculate progress for protein
            double consumedProtein = consumptionRecords.stream()
                    .mapToDouble(cr -> cr.getQuantity() * cr.getItem().getProtein()).sum();
            progress.put("protein", (double) consumedProtein * 100 / targetProtein);

            // Calculate progress for fat
            double consumedFat = consumptionRecords.stream()
                    .mapToDouble(cr -> cr.getQuantity() * cr.getItem().getFat()).sum();
            progress.put("fat", (double) consumedFat * 100 / nutritionTarget.getTargetFat());
        }
        return progress;
    }

}
