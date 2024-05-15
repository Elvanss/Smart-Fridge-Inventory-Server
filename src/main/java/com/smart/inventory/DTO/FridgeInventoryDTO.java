package com.smart.inventory.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FridgeInventoryDTO {
    private Long id;
    private SharedFridgeDTO sharedFridge;
    private Integer numberOfItems;
}
