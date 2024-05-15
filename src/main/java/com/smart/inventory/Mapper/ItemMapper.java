package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.ItemDTO;
import com.smart.inventory.Entity.Item;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ItemMapper {

    private final FridgeInventoryMapper fridgeInventoryMapper;

    public ItemMapper(FridgeInventoryMapper fridgeInventoryMapper) {
        this.fridgeInventoryMapper = fridgeInventoryMapper;
    }

    public ItemDTO convertToItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setCategory(item.getCategory());
        itemDTO.setStock(item.getStock());
        itemDTO.setCalories(item.getCalories());
        itemDTO.setProtein(item.getProtein());
        itemDTO.setFat(item.getFat());
        itemDTO.setStockStatus(item.getStockStatus());
        itemDTO.setPurchaseDate(item.getPurchaseDate());
        itemDTO.setExpiryDate(item.getExpiryDate());
        itemDTO.setDaysLeft(item.getDaysLeft());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setFridgeInventory(item.getFridgeInventory() != null
                                            ? this.fridgeInventoryMapper.toFridgeInventoryDTO(item.getFridgeInventory())
                                            : null);
        itemDTO.setNumberOfConsumptionRecords(item.getNumberOfConsumptionRecords());
        return itemDTO;
    }

    public Item convertToItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setCategory(itemDTO.getCategory());
        item.setStock(itemDTO.getStock());
        item.setCalories(itemDTO.getCalories());
        item.setProtein(itemDTO.getProtein());
        item.setFat(itemDTO.getFat());
        item.setStockStatus(itemDTO.getStockStatus());
        item.setPurchaseDate(itemDTO.getPurchaseDate());
        item.setExpiryDate(itemDTO.getExpiryDate());
        item.setDaysLeft(itemDTO.getDaysLeft());
        item.setDescription(itemDTO.getDescription());
        return item;
    }
}
