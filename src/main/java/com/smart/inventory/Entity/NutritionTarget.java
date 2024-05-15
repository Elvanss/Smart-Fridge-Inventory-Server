package com.smart.inventory.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// NutritionTarget.java
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "nutrition")
public class NutritionTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @Column(name = "target_calories")
    private Integer targetCalories;

    @Column(name = "target_protein")
    private Integer targetProtein;

    @Column(name = "target_fat")
    private Integer targetFat;

}
