package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.FridgeInventoryDTO;
import com.smart.inventory.Entity.FridgeInventory;
import com.smart.inventory.Entity.Item;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FridgeInventoryMapper {

    private final ItemMapper itemMapper;

    public FridgeInventoryMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public FridgeInventoryDTO convertToFridgeInventoryDTO(FridgeInventory fridgeInventory) {
        FridgeInventoryDTO fridgeInventoryDTO = new FridgeInventoryDTO();
        fridgeInventoryDTO.setId(fridgeInventory.getId());
        fridgeInventoryDTO.setLocation(fridgeInventory.getLocation());
        fridgeInventoryDTO.setCapacity(fridgeInventory.getCapacity());
        fridgeInventoryDTO.setUserId(fridgeInventory.getUser().getId());
//        fridgeInventoryDTO.setNumberOfItems(fridgeInventory.getNumberOfItems());
        fridgeInventoryDTO.setItems(fridgeInventory.getItem().stream()
                .map(itemMapper::convertToItemDTO)
                .collect(Collectors.toList()));
        return fridgeInventoryDTO;
    }



//    private FridgeInventoryDTO convertToFridgeInventoryDTO(FridgeInventory fridgeInventory) {
//        FridgeInventoryDTO fridgeInventoryDTO = new FridgeInventoryDTO();
//        fridgeInventoryDTO.setId(fridgeInventory.getId());
//        fridgeInventoryDTO.setLocation(fridgeInventory.getLocation());
//        fridgeInventoryDTO.setCapacity(fridgeInventory.getCapacity());
//        fridgeInventoryDTO.setUserId(fridgeInventory.getUser().getId());
//        fridgeInventoryDTO.setNumberOfItems(fridgeInventory.getNumberOfItems());
//        fridgeInventoryDTO.setItems(fridgeInventory.getItem().stream()
//                .map(this::convertToItemDTO)
//                .collect(Collectors.toList()));
//        return fridgeInventoryDTO;
//    }
}
