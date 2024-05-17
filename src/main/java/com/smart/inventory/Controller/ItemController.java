package com.smart.inventory.Controller;

import com.smart.inventory.DTO.ItemDTO;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Mapper.ItemMapper;
import com.smart.inventory.Service.ItemService;
import com.smart.inventory.Service.ProfileService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ProfileService profileService;

    @GetMapping
    public Result getItems() {
        List<Item> items = itemService.getItems();
        List<ItemDTO> itemDTOS = items.stream()
                .map(itemMapper::convertToItemDTO)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "All Items", itemDTOS);
    }

    @PutMapping("/update/{id}")
    public Result updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        Item item = itemMapper.convertToItem(itemDTO);
        Item updatedItem = itemService.updateItem(id, item);
        ItemDTO updatedItemDTO = itemMapper.convertToItemDTO(updatedItem);
        return new Result(true, StatusCode.SUCCESS, "Item Updated", updatedItemDTO);
    }

    // Requirement 2: Add item to fridge
    @PostMapping("/{profileId}/add")
    public Result addItemToFridgeInventory(@PathVariable Long profileId, @RequestBody Item item) {
        Profile profile = profileService.getProfileById(profileId);
        if (profile == null) {
            return new Result(false, StatusCode.NOT_FOUND, "Profile not found");
        }

        Item savedItem = itemService.addItemToFridge(profile, item);
        ItemDTO itemDTO = itemMapper.convertToItemDTO(savedItem);
        return new Result(true, StatusCode.SUCCESS, "Item Added to Fridge", itemDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteItem(@PathVariable Long id) {
        this.itemService.deleteItem(id);
        return new Result(true, StatusCode.SUCCESS, "Item Deleted");
    }

    // Requirement 2: Search item by name
    @GetMapping("/search")
    public Result searchItemLeastByCharacter(@RequestParam("item") String name) {
        List<Item> items = itemService.searchItemLeastByCharacter(name);
        List<ItemDTO> itemDTOS = items.stream()
                .map(itemMapper::convertToItemDTO)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Search Results", itemDTOS);
    }

    @GetMapping("/fridgeInventory/{userId}")
    public Result getFridgeInventoryForUser(@PathVariable Long userId) {
        User user = new User(); // You need to get the user based on the userId
        List<Item> items = itemService.getFridgeInventoryForUser(user);
        List<ItemDTO> itemDTOS = items.stream()
                .map(itemMapper::convertToItemDTO)
                .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Fridge Inventory", itemDTOS);
    }

    // Requirement 2: Remove item from fridge
    @DeleteMapping("/removeFromFridge/{itemId}")
    public Result removeItemFromFridge(@PathVariable Item itemId) {
        itemService.removeItemFromFridge(itemId);
        return new Result(true, StatusCode.SUCCESS, "Item Removed From Fridge");
    }

    @PostMapping("/updateFridgeInventory/{userId}")
    public Result updateFridgeInventoryFromSmartFridge(@PathVariable Long userId, @RequestBody List<ItemDTO> updatedItemsDTO) {
        User user = new User(); // You need to get the user based on the userId
        List<Item> updatedItems = updatedItemsDTO.stream()
                .map(itemMapper::convertToItem)
                .collect(Collectors.toList());
        itemService.updateFridgeInventoryFromSmartFridge(user, updatedItems);
        return new Result(true, StatusCode.SUCCESS, "Fridge Inventory Updated");
    }


}