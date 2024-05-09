package com.smart.inventory.Service;

import com.smart.inventory.Entity.Item;
import com.smart.inventory.Repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Get all items that belong to a fridge
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    // Add an item to the fridge
    public Item addItem (Item item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setCategory(item.getCategory());
        newItem.setStock(item.getStock());
        newItem.setCalories(item.getCalories());
        newItem.setStockStatus(item.getStockStatus());
        newItem.setPurchaseDate(item.getPurchaseDate());
        newItem.setExpiryDate(item.getExpiryDate());
        if (item.getExpiryDate() != null && item.getPurchaseDate() != null) {
            newItem.setDaysLeft(item.getExpiryDate().toEpochDay() - item.getPurchaseDate().toEpochDay());
        }
        newItem.setDescription(item.getDescription());
        return this.itemRepository.save(newItem);
    }

    // Update an item in the fridge
    public Item updateItem (Long id, Item item) {
        item = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found! "));
        item.setName(item.getName());
        item.setCategory(item.getCategory());
        item.setStock(item.getStock());
        item.setCalories(item.getCalories());
        item.setStockStatus(item.getStockStatus());
        item.setPurchaseDate(item.getPurchaseDate());
        item.setExpiryDate(item.getExpiryDate());
//        itemValid.setDaysLeft(itemValid.getExpiryDate().toEpochDay() - itemValid.getPurchaseDate().toEpochDay());
        item.setDescription(item.getDescription());

        return item;
    }

    // Add multiple items to the fridge
    public List<Item> addItems(List<Item> items) {
        return items.stream()
                .map(this::addItem)
                .toList();
    }

    // Delete an item from the fridge
    public void deleteItem(Long id) {
        Item itemValid = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found! "));
        itemRepository.delete(itemValid);
    }

    // I need to make a method if I type as least the any character of the items name, it will show the items that contain the character.
    public List<Item> searchItemLeastByCharacter(String name) {
        return this.itemRepository.findByNameContaining(name);
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
