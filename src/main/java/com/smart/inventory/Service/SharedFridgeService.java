package com.smart.inventory.Service;

import com.smart.inventory.Entity.FridgeInventory;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.SharedFridge;
import com.smart.inventory.Repository.FridgeInventoryRepository;
import com.smart.inventory.Repository.SharedFridgeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SharedFridgeService {

    private final SharedFridgeRepository sharedFridgeRepository;
    private final FridgeInventoryRepository fridgeInventoryRepository;

    public SharedFridgeService(SharedFridgeRepository sharedFridgeRepository,
                               FridgeInventoryRepository fridgeInventoryRepository) {
        this.sharedFridgeRepository = sharedFridgeRepository;
        this.fridgeInventoryRepository = fridgeInventoryRepository;
    }

    public SharedFridge saveSharedFridge(SharedFridge sharedFridge) {
        return sharedFridgeRepository.save(sharedFridge);
    }

    public List<SharedFridge> getAllSharedFridges() {
        return sharedFridgeRepository.findAll();
    }

    // Requirement 2: Get all items in a shared fridge (all items in all fridge inventories) by shared fridge id
    public List<Item> getAllItemsInSharedFridge(Long sharedFridgeId) {
        SharedFridge sharedFridge = sharedFridgeRepository.findById(sharedFridgeId)
                .orElseThrow(() -> new RuntimeException("SharedFridge not found"));

        List<Item> allItems = new ArrayList<>();
        for (FridgeInventory fridgeInventory : sharedFridge.getFridgeInventories()) {
            allItems.addAll(fridgeInventory.getItems());
            for (Item item : fridgeInventory.getItems()) {
                if (item.getStock() == 0) {
                    fridgeInventory.removeItem(item);
                }
            }
        }
        return allItems;
    }

    // Requirement 2: Get the shared fridge by id
    public SharedFridge getSharedFridgeById(Long id) {
        return sharedFridgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SharedFridge not found"));
    }

    public FridgeInventory saveFridgeInventory(FridgeInventory fridgeInventory) {
        return fridgeInventoryRepository.save(fridgeInventory);
    }

    public List<FridgeInventory> getAllFridgeInventories() {
        return fridgeInventoryRepository.findAll();
    }

    public List<FridgeInventory> getFridgeInventoriesBySharedFridge(SharedFridge sharedFridge) {
        return fridgeInventoryRepository.findBySharedFridge(sharedFridge);
    }

    public FridgeInventory getFridgeInventoryByProfile(Profile profile) {
        return fridgeInventoryRepository.findByProfile(profile);
    }

    public FridgeInventory getFridgeInventoryById(Long id) {
        return fridgeInventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FridgeInventory not found"));
    }

    public void assignFridgeInventory(Long fridgeInventoryId, Long sharedFridgeId) {
        FridgeInventory fridgeInventoryToBeAssigned = fridgeInventoryRepository.findById(fridgeInventoryId)
                .orElseThrow(() -> new RuntimeException("FridgeInventory not found"));

        SharedFridge sharedFridge = sharedFridgeRepository.findById(sharedFridgeId)
                .orElseThrow(() -> new RuntimeException("SharedFridge not found"));

        if (fridgeInventoryToBeAssigned.getSharedFridge() != null) {
            fridgeInventoryToBeAssigned.getSharedFridge().removeFridgeInventory(fridgeInventoryToBeAssigned);
        }

        sharedFridge.addFridgeInventory(fridgeInventoryToBeAssigned);
    }


}
