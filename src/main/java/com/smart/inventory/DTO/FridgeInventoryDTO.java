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
    private String location;
    private Integer capacity;
    private Long userId;
//    private Integer numberOfItems;
    private List<ItemDTO> items; // Add this line
}
