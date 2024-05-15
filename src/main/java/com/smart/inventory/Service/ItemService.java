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
        item = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found! "));
        item.setName(item.getName());
        item.setCategory(item.getCategory());
        item.setStock(item.getStock());
        item.setCalories(item.getCalories());
        item.setProtein(item.getProtein());
        item.setFat(item.getFat());
        item.setStockStatus(item.getStockStatus());
        item.setPurchaseDate(item.getPurchaseDate());
        item.setExpiryDate(item.getExpiryDate());
//        itemValid.setDaysLeft(itemValid.getExpiryDate().toEpochDay() - itemValid.getPurchaseDate().toEpochDay());
        item.setDescription(item.getDescription());

        return item;
    }

    public Item addItemToFridge(Profile profile, Item item) {
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
         newItem.setFridgeInventory(profile.getFridgeInventory());
         Item savedItem = itemRepository.save(newItem);
         profile.setFridgeInventory(item.getFridgeInventory());
         profile.addItemToFridgeInventory(savedItem);
         return itemRepository.save(savedItem);
    }


//    // Add multiple items to the fridge
//    public List<Item> addItems(List<Item> items) {
//        return items.stream()
//                .map(this::addItemToFridge)
//                .toList();
//    }

    // Delete an item from the fridge
    public void deleteItem(Long id) {
        Item itemValid = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found! "));
        itemRepository.delete(itemValid);
    }

    public List<Item> searchItemLeastByCharacter(String name) {
        return this.itemRepository.findByNameContaining(name);
    }

    public List<Item> getFridgeInventoryForUser(User user) {
        return user.getSharedFridge().getFridgeInventories().get(0).getItems();
    }

    public void updateItemStock(Item item, Integer newStock) {
        item.setStock(newStock);
    }

    public void removeItemFromFridge(Item item) {
        FridgeInventory fridgeInventory = item.getFridgeInventory();
        fridgeInventory.removeItem(item);
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
