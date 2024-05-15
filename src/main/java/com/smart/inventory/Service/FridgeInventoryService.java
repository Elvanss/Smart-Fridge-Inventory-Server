package com.smart.inventory.Service;

import com.smart.inventory.Entity.FridgeInventory;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Repository.FridgeInventoryRepository;
import com.smart.inventory.Repository.ItemRepository;
import com.smart.inventory.System.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class FridgeInventoryService {

    private final FridgeInventoryRepository fridgeInventoryRepository;
    private final ItemRepository itemRepository;

    public FridgeInventoryService(FridgeInventoryRepository fridgeInventoryRepository,
                                  ItemRepository itemRepository) {
        this.fridgeInventoryRepository = fridgeInventoryRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public List<FridgeInventory> getAllFridgeInventory() {
        return this.fridgeInventoryRepository.findAll();
    }

    // Add a new method to get a fridge inventory by id.
    public FridgeInventory getFridgeInventoryById(Long id) {
        return this.fridgeInventoryRepository.findById(id).orElse(null);
    }

    // Add a new method to save a fridge inventory.
    public FridgeInventory saveFridgeInventory(FridgeInventory fridgeInventory) {
        return this.fridgeInventoryRepository.save(fridgeInventory);
    }

    public FridgeInventory updateFridgeInventory(Long id, FridgeInventory fridgeInventory) {
        FridgeInventory existingFridgeInventory = this.fridgeInventoryRepository.findById(id).orElse(null);
        if (existingFridgeInventory == null) {
            return null;
        }
        existingFridgeInventory.setSharedFridge(fridgeInventory.getSharedFridge());
        existingFridgeInventory.setItems(fridgeInventory.getItems());
        return this.fridgeInventoryRepository.save(existingFridgeInventory);
    }

    // Add a new method to delete a fridge inventory.
    public void deleteFridgeInventory(Long id) {
        FridgeInventory fridgeToBeDeleted = this.fridgeInventoryRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Fridge Not Found!", id));
        fridgeToBeDeleted.removeItems();
        this.fridgeInventoryRepository.deleteById(id);
    }

//    // Assign an item to a fridge inventory.
//    public void assignItem(Long fridgeInventoryId, Long itemId) {
//
//        // Find this item by Id from DB.
//        Item itemToBeAssigned  = this.itemRepository.findById(itemId)
//                .orElseThrow(()-> new ObjectNotFoundException("Item Not Found! ", itemId));
//
//        // Find this fridge inventory by Id from DB.
//        FridgeInventory fridgeInventory = this.fridgeInventoryRepository.findById(fridgeInventoryId)
//                    .orElseThrow(()-> new ObjectNotFoundException("Fridge Inventory Not Found! ", fridgeInventoryId));
//
//        // Item assignment
//        if (itemToBeAssigned.getFridgeInventory() != null) {
//            itemToBeAssigned.getFridgeInventory().removeItem(itemToBeAssigned);
//        }
//
//        // Add the item to the fridge inventory.
//        fridgeInventory.addItem(itemToBeAssigned);
//        this.fridgeInventoryRepository.save(fridgeInventory);
//    }
//
//    public void assignItemsToFridge(Long fridgeInventoryId, List<Long> itemIds) {
//        FridgeInventory fridgeInventory = fridgeInventoryRepository.findById(fridgeInventoryId)
//            .orElseThrow(() -> new ObjectNotFoundException("Fridge Inventory Not Found! ", fridgeInventoryId));
//
//        List<Item> items = itemIds.stream()
//            .map(itemId -> itemRepository.findById(itemId)
//                .orElseThrow(() -> new ObjectNotFoundException("Item Not Found! ", itemId)))
//            .toList();
//
//        items.forEach(item -> {
//            if (item.getFridgeInventory() != null) {
//                item.getFridgeInventory().removeItem(item);
//            }
//            fridgeInventory.addItem(item);
//        });
//
//        this.fridgeInventoryRepository.save(fridgeInventory);
//    }





}
