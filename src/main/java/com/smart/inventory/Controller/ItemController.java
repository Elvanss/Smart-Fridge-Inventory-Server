package com.smart.inventory.Controller;

import com.smart.inventory.DTO.ItemDTO;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Mapper.ItemMapper;
import com.smart.inventory.Service.ItemService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    // Get all items that belong to a fridge
    @GetMapping
    public Result getItems() {
        List<Item> items = this.itemService.getItems();
        List<ItemDTO> itemDTOS = items.stream()
                .map(itemMapper::convertToItemDTO)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "All Items", itemDTOS);
    }

    // Add an item to the fridge
    @PostMapping("/add")
    public Result addItem(@RequestBody ItemDTO itemDTO) {
        Item item = this.itemMapper.convertToItem(itemDTO);
        Item newItem = this.itemService.addItem(item);
        ItemDTO newItemDTO = this.itemMapper.convertToItemDTO(newItem);
        return new Result(true, StatusCode.SUCCESS, "Item Added", newItemDTO);
    }

    // Update an item in the fridge
    @PutMapping("/update/{id}")
    public Result updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        Item item = this.itemMapper.convertToItem(itemDTO);
        Item updatedItem = this.itemService.updateItem(id, item);
        ItemDTO updatedItemDTO = this.itemMapper.convertToItemDTO(updatedItem);
        return new Result(true, StatusCode.SUCCESS, "Item Updated", updatedItemDTO);
    }

    @PostMapping("/add-multiple")
    public Result addItems(@RequestBody List<ItemDTO> itemDTOs) {
        List<Item> items = itemDTOs.stream()
            .map(itemMapper::convertToItem)
            .collect(Collectors.toList());
        List<Item> newItems = this.itemService.addItems(items);
        List<ItemDTO> newItemDTOs = newItems.stream()
            .map(itemMapper::convertToItemDTO)
            .collect(Collectors.toList());
        return new Result(true, StatusCode.SUCCESS, "Items Added", newItemDTOs);
    }


}
