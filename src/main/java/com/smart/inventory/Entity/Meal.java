package com.smart.inventory.Entity;

import com.smart.inventory.Entity.Type.DietaryList;
import com.smart.inventory.Entity.Type.RoleList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "meal-image")
    private String mealImageUrl;

    @Column(name = "cooking_time")
    private String cookingTime;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> ingredients = new ArrayList<>();

    @ElementCollection(targetClass = DietaryList.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<DietaryList> dietaryLists = new ArrayList<>();

    @Column(length = 1000)
    private String cookingMethod;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

}

