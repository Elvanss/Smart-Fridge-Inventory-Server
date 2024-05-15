package com.smart.inventory.DTO;

import com.smart.inventory.Entity.Type.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDTO {
    private Long id;
    private String name;
    private String category;
    private Integer stock;
    private Double calories;
    private Double protein;
    private Double fat;
    private StockStatus stockStatus;
    private LocalDate purchaseDate;
    private LocalDate expiryDate;
    private Long daysLeft;
    private String description;
    private FridgeInventoryDTO fridgeInventory;
    private Integer numberOfConsumptionRecords;
}
