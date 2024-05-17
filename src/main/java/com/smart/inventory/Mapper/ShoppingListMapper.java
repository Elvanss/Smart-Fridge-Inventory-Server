package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.ShoppingListDTO;
import com.smart.inventory.Entity.ShoppingList;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapper {
    public ShoppingListDTO toDTO(ShoppingList shoppingList) {
        ShoppingListDTO shoppingListDTO = new ShoppingListDTO();
        shoppingListDTO.setId(shoppingList.getId());
        shoppingListDTO.setName(shoppingList.getName());
        shoppingListDTO.setItems(shoppingList.getItems() != null
                ? shoppingList.getItems()
                : null);
        shoppingListDTO.setCost(shoppingList.getCost());
        shoppingListDTO.setEstimatedDate(shoppingList.getEstimatedDate());
        shoppingListDTO.setDescription(shoppingList.getDescription());
        shoppingListDTO.setNumberOfUser(shoppingList.numberOfUser());

        return shoppingListDTO;
    }

    public ShoppingList toEntity(ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(shoppingListDTO.getId());
        shoppingList.setName(shoppingListDTO.getName());
        shoppingList.setItems(shoppingListDTO.getItems());
        shoppingList.setCost(shoppingListDTO.getCost());
        shoppingList.setEstimatedDate(shoppingListDTO.getEstimatedDate());
        shoppingList.setDescription(shoppingListDTO.getDescription());
        return shoppingList;
    }
}
