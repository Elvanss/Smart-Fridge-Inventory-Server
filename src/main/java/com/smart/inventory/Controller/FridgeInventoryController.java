package com.smart.inventory.Controller;

import com.smart.inventory.DTO.FridgeInventoryDTO;
import com.smart.inventory.Entity.FridgeInventory;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Mapper.FridgeInventoryMapper;
import com.smart.inventory.Service.FridgeInventoryService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fridge-inventory")
public class FridgeInventoryController {

    private final FridgeInventoryService fridgeInventoryService;
    private final FridgeInventoryMapper fridgeInventoryMapper;



    public FridgeInventoryController(FridgeInventoryService fridgeInventoryService, FridgeInventoryMapper fridgeInventoryMapper) {
        this.fridgeInventoryService = fridgeInventoryService;
        this.fridgeInventoryMapper = fridgeInventoryMapper;
    }

    // Add a new method to get all fridge inventories.
    @GetMapping
    public Result getAllFridgeInventory() {
        List<FridgeInventory> fridgeInventories = this.fridgeInventoryService.getAllFridgeInventory();
        return new Result(true, StatusCode.SUCCESS, "Find Fridge Success", fridgeInventories.stream()
                .map(fridgeInventoryMapper::toFridgeInventoryDTO)
                .collect(Collectors.toList()));

    }

    // Add a new method to get a fridge inventory by id.
    @GetMapping("/{id}")
    public Result getFridgeInventoryById(@PathVariable Long id) {
        FridgeInventory fridgeInventory = this.fridgeInventoryService.getFridgeInventoryById(id);
        FridgeInventoryDTO fridgeInventoryDTO = fridgeInventoryMapper.toFridgeInventoryDTO(fridgeInventory);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", fridgeInventoryDTO);
    }

//    @PutMapping("/assign/{fridgeId}/{itemId}")
//    public Result assignItemToFridge(@PathVariable Long fridgeId, @PathVariable Long itemId) {
//        this.fridgeInventoryService.assignItem(fridgeId, itemId);
//        return new Result(true, StatusCode.SUCCESS,  "Item " + itemId + " Assigned to Fridge " + fridgeId, null);
//    }
//
//    @PutMapping("assigns/{fridgeId}/{itemIds}")
//    public Result assignItemsToFridge(@PathVariable Long fridgeId, @PathVariable String itemIds) {
//        List<Long> itemIdList = Arrays.stream(itemIds.split("-"))
//            .map(Long::valueOf)
//            .collect(Collectors.toList());
//        this.fridgeInventoryService.assignItemsToFridge(fridgeId, itemIdList);
//        return new Result(true, StatusCode.SUCCESS, "Items Assigned to Fridge " + fridgeId, null);
//}









}
