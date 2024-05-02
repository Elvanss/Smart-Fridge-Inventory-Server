package com.smart.inventory.Service;

import com.smart.inventory.Entity.Item;
import com.smart.inventory.Repository.ItemRepository;
import org.hibernate.ObjectNotFoundException;
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
        Item itemValid = itemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Item Not Found! "));
        itemValid.setName(item.getName());
        itemValid.setCategory(item.getCategory());
        itemValid.setStock(item.getStock());
        itemValid.setCalories(item.getCalories());
        itemValid.setStockStatus(item.getStockStatus());
        itemValid.setPurchaseDate(item.getPurchaseDate());
        itemValid.setExpiryDate(item.getExpiryDate());
//        itemValid.setDaysLeft(itemValid.getExpiryDate().toEpochDay() - itemValid.getPurchaseDate().toEpochDay());
        itemValid.setDescription(item.getDescription());

        return itemValid;
    }

    // Add multiple items to the fridge
    public List<Item> addItems(List<Item> items) {
        return items.stream()
                .map(this::addItem)
                .toList();
    }

    // Delete an item from the fridge
    public void deleteItem (Long id) {
        this.itemRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Item Not Found!", id));
        this.itemRepository.deleteById(id);
    }
}
