package com.smart.inventory.Controller;

import com.smart.inventory.DTO.ItemDTO;
import com.smart.inventory.DTO.SharedFridgeDTO;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Entity.SharedFridge;
import com.smart.inventory.Mapper.ItemMapper;
import com.smart.inventory.Mapper.ShareFridgeMapper;
import com.smart.inventory.Service.SharedFridgeService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sharedFridge")
public class SharedFridgeController {

    private final SharedFridgeService sharedFridgeService;
    private final ShareFridgeMapper sharedFridgeMapper;
    private final ItemMapper itemMapper;

    public SharedFridgeController(SharedFridgeService sharedFridgeService,
                                  ShareFridgeMapper sharedFridgeMapper,
                                  ItemMapper itemMapper) {
        this.sharedFridgeService = sharedFridgeService;
        this.sharedFridgeMapper = sharedFridgeMapper;
        this.itemMapper = itemMapper;
    }

    // Requirement 2: Get all shared fridges for a user.
    @GetMapping
    public Result findAllSharedFridges() {
        List<SharedFridge> foundSharedFridge = this.sharedFridgeService.getAllSharedFridges();

        List<SharedFridgeDTO> sharedFridgeDtos = foundSharedFridge.stream()
                .map(this.sharedFridgeMapper::toSharedFridgeDTO)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Find All Success", sharedFridgeDtos);
    }

    // Requirement 2: Get a shared fridge by id.
    @GetMapping("/{id}")
    public Result findSharedFridgeById(@PathVariable Long id) {
        SharedFridge foundSharedFridge = this.sharedFridgeService.getSharedFridgeById(id);
        SharedFridgeDTO sharedFridgeDTO = this.sharedFridgeMapper.toSharedFridgeDTO(foundSharedFridge);
        return new Result(true, StatusCode.SUCCESS, "Find One Success", sharedFridgeDTO);
    }


    // Requirement 2: Get all items in a shared fridge (all items in all fridge inventories) by shared fridge id
    @GetMapping("/{sharedFridgeId}/items")
    public Result getAllItemsInSharedFridge(@PathVariable Long sharedFridgeId) {
        List<Item> items = sharedFridgeService.getAllItemsInSharedFridge(sharedFridgeId);
        List<ItemDTO> itemDTOs = items.stream()
                .map(this.itemMapper::convertToItemDTO)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "All Items in SharedFridge", itemDTOs);
    }

}
