package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.FridgeInventoryDTO;
import com.smart.inventory.Entity.FridgeInventory;
import com.smart.inventory.Entity.Item;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FridgeInventoryMapper {

    private final ShareFridgeMapper sharedFridgeMapper;

    public FridgeInventoryMapper(ShareFridgeMapper sharedFridgeMapper) {
        this.sharedFridgeMapper = sharedFridgeMapper;
    }

    public FridgeInventoryDTO toFridgeInventoryDTO(FridgeInventory fridgeInventory) {
        FridgeInventoryDTO fridgeInventoryDTO = new FridgeInventoryDTO();
        fridgeInventoryDTO.setId(fridgeInventory.getId());
        fridgeInventoryDTO.setSharedFridge(fridgeInventory.getSharedFridge() != null
                ? this.sharedFridgeMapper.toSharedFridgeDTO(fridgeInventory.getSharedFridge())
                : null);
        fridgeInventoryDTO.setNumberOfItems(fridgeInventory.getNumberOfItems());
        return fridgeInventoryDTO;
    }

    public FridgeInventory toFridgeInventory(FridgeInventoryDTO fridgeInventoryDTO) {
        FridgeInventory fridgeInventory = new FridgeInventory();
        fridgeInventory.setSharedFridge(fridgeInventoryDTO.getSharedFridge() != null
                ? this.sharedFridgeMapper.toSharedFridge(fridgeInventoryDTO.getSharedFridge())
                : null);
        return fridgeInventory;
    }
}
