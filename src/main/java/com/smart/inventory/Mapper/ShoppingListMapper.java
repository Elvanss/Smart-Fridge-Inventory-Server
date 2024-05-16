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
        shoppingListDTO.setDescription(shoppingList.getDescription());
        shoppingListDTO.setUser(shoppingList.getUser() == null
                                                    ? new UserMapper().convertToDto(shoppingList.getUser())
                                                    : null);
        shoppingListDTO.setItems(shoppingList.getItems());
        return shoppingListDTO;
    }

    public ShoppingList toEntity(ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setId(shoppingListDTO.getId());
        shoppingList.setName(shoppingListDTO.getName());
        shoppingList.setDescription(shoppingListDTO.getDescription());
        return shoppingList;
    }
}
