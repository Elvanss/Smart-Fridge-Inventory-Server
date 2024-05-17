package com.smart.inventory.DTO;

import com.smart.inventory.Entity.Type.DietaryList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportDTO {
    private String profileName;
    private Integer profileAge;
    private DietaryList profileDietary;
    private List<String> consumedItems;
    // Remaining fields
    private Double totalCaloriesRemaining;
    private Double totalProteinRemaining;
    private Double totalFatRemaining;
    // Consumed fields
    private Double totalCaloriesConsumed;
    private Double totalProteinConsumed;
    private Double totalFatConsumed;
}
