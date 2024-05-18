package com.smart.inventory.Service;

import com.smart.inventory.Entity.*;
import com.smart.inventory.Repository.FridgeInventoryRepository;
import com.smart.inventory.Repository.ItemRepository;
import com.smart.inventory.Repository.ProfileRepository;
import com.smart.inventory.Repository.SharedFridgeRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProfileRepository profileRepository;
    private final FridgeInventoryRepository fridgeInventoryRepository;

    public ItemService(ItemRepository itemRepository,
                       ProfileRepository profileRepository, FridgeInventoryRepository fridgeInventoryRepository) {
        this.itemRepository = itemRepository;
        this.profileRepository = profileRepository;
        this.fridgeInventoryRepository = fridgeInventoryRepository;
    }

    // Get all items that belong to a fridge
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    // Update an item in the fridge
    public Item updateItem (Long id, Item item) {
        Item itemSaved = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found! "));
        itemSaved.setName(item.getName());
        itemSaved.setCategory(item.getCategory());
        itemSaved.setStock(item.getStock());
        itemSaved.setCalories(item.getCalories());
        itemSaved.setProtein(item.getProtein());
        itemSaved.setFat(item.getFat());
        itemSaved.setStockStatus(item.getStockStatus());
        itemSaved.setPurchaseDate(item.getPurchaseDate());
        itemSaved.setExpiryDate(item.getExpiryDate());
//        itemValid.setDaysLeft(itemValid.getExpiryDate().toEpochDay() - itemValid.getPurchaseDate().toEpochDay());
        itemSaved.setDescription(item.getDescription());
        return itemRepository.save(itemSaved);
    }

//    public Item addItemToFridge(Profile profile, Item item) {
//        // Step 1: Create Item
//         Item newItem = new Item();
//         newItem.setName(item.getName());
//         newItem.setCategory(item.getCategory());
//         newItem.setStock(item.getStock());
//         newItem.setCalories(item.getCalories());
//         newItem.setProtein(item.getProtein());
//         newItem.setFat(item.getFat());
//         newItem.setStockStatus(item.getStockStatus());
//         newItem.setPurchaseDate(item.getPurchaseDate());
//         newItem.setExpiryDate(item.getExpiryDate());
//         newItem.setDaysLeft(item.getExpiryDate().toEpochDay() - item.getPurchaseDate().toEpochDay());
//         newItem.setDescription(item.getDescription());
//         // Step 2: Save Item
//         Item savedItem = itemRepository.save(newItem);
//         // Step 3: Add Item to Fridge Inventory
//         profile.setFridgeInventory(item.getFridgeInventory());
//         // Step 4: Add Item to Profile
//         profile.addItemToFridgeInventory(savedItem);
//         return itemRepository.save(savedItem);
//    }

    public Item addItemToFridge(Profile profile, Item item) {
        // Step 1: Create Item
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setCategory(item.getCategory());
        newItem.setStock(item.getStock());
        newItem.setCalories(item.getCalories());
        newItem.setProtein(item.getProtein());
        newItem.setFat(item.getFat());
        newItem.setStockStatus(item.getStockStatus());
        newItem.setPurchaseDate(item.getPurchaseDate());
        newItem.setExpiryDate(item.getExpiryDate());
        newItem.setDaysLeft(item.getExpiryDate().toEpochDay() - item.getPurchaseDate().toEpochDay());
        newItem.setDescription(item.getDescription());

        // Step 2: Save or get the FridgeInventory
        FridgeInventory fridgeInventory;
        if (item.getFridgeInventory() != null && item.getFridgeInventory().getId() != null) {
            fridgeInventory = fridgeInventoryRepository.findById(item.getFridgeInventory().getId())
                    .orElseThrow(() -> new RuntimeException("FridgeInventory not found"));
        } else {
            fridgeInventory = profile.getFridgeInventory();
            if (fridgeInventory == null) {
                fridgeInventory = new FridgeInventory();
                fridgeInventory.setProfile(profile);
                fridgeInventory = fridgeInventoryRepository.save(fridgeInventory);
            }
        }

        // Step 3: Associate the Item with the FridgeInventory
        newItem.setFridgeInventory(fridgeInventory);
        // Step 4: Save the Item
        return itemRepository.save(newItem);
    }


//    // Add multiple items to the fridge
//    public List<Item> addItems(List<Item> items) {
//        return items.stream()
//                .map(this::addItemToFridge)
//                .toList();
//    }



    public List<Item> searchItemLeastByCharacter(String name) {
        return this.itemRepository.findByNameContaining(name);
    }

    public List<Item> getFridgeInventoryForUser(User user) {
        return user.getSharedFridge().getFridgeInventories().get(0).getItems();
    }

    @Transactional
    public void removeItemFromFridge(Item item) {
        FridgeInventory fridgeInventory = item.getFridgeInventory();
        fridgeInventory.removeItem(item);
    }

    // Delete an item from the fridge
    @Transactional
    public void deleteItem(Long id) {
        this.itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found! "));
        this.itemRepository.deleteById(id);
    }

    public void updateFridgeInventoryFromSmartFridge(User user, List<Item> updatedItems) {
        FridgeInventory fridgeInventory = user.getSharedFridge().getFridgeInventories().get(0);
        fridgeInventory.removeItems();
        for (Item item : updatedItems) {
            fridgeInventory.addItem(item);
        }
    }


    @Scheduled(cron = "0 0 0 * * ?")
    public void ItemAlertByDayLeft() {
        List<Item> items = this.itemRepository.findAll();
        for (Item item : items) {
            if (item.getDaysLeft() <= 3) {
                System.out.println("Item " + item.getName() + " is going to expire in " + item.getDaysLeft() + " days.");
            }
        }
    }
}
