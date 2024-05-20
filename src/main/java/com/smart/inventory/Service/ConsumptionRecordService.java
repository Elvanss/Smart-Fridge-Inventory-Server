package com.smart.inventory.Service;

import com.smart.inventory.Entity.*;
import com.smart.inventory.Repository.ConsumptionRecordRepository;
import com.smart.inventory.Repository.ItemRepository;
import com.smart.inventory.Repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumptionRecordService {

    private final ConsumptionRecordRepository consumptionRecordRepository;
    private final ProfileRepository profileRepository;
    private final ItemRepository itemRepository;

    public ConsumptionRecordService(ConsumptionRecordRepository consumptionRecordRepository, ProfileRepository profileRepository, ItemRepository itemRepository) {
        this.consumptionRecordRepository = consumptionRecordRepository;
        this.profileRepository = profileRepository;
        this.itemRepository = itemRepository;
    }

    public ConsumptionRecord saveConsumptionRecord(ConsumptionRecord consumptionRecord) {
        return consumptionRecordRepository.save(consumptionRecord);
    }

    public List<ConsumptionRecord> getAllConsumptionRecords() {
        return consumptionRecordRepository.findAll();
    }

    public List<ConsumptionRecord> getConsumptionRecordsByProfile(Long profileId) {
        return consumptionRecordRepository.findAllByProfile(profileId);
    }

    // Requirement 4: Transfer item to consumption record
//    public void transferItemToComsumptionRecord(Long profileId, Long itemId, Integer quantity) {
//        Profile profile = profileRepository.findById(profileId)
//                .orElseThrow(() -> new RuntimeException("Profile not found"));
//
//        FridgeInventory fridgeInventory = profile.getFridgeInventory();
//        if (fridgeInventory == null) {
//            throw new RuntimeException("FridgeInventory not found");
//        }
//
//        Item item = fridgeInventory.getItems().stream() // get all items in the fridge
//                .filter(i -> i.getId().equals(itemId))// filter the item by id
//                .findFirst() // get the first item
//                .orElseThrow(() -> new RuntimeException("Item not found"));
//
//        ConsumptionRecord consumptionRecord = new ConsumptionRecord();
//        consumptionRecord.setItem(item);
//        consumptionRecord.setQuantity(quantity);
//        consumptionRecord.setConsumedAt(LocalDateTime.now());
//        consumptionRecord.setProfile(profile);
//        profile.transferItemToConsumptionRecord(item, quantity);
//        itemRepository.save(item);
//        this.consumptionRecordRepository.save(consumptionRecord);
//    }

    @Transactional
    public void transferItemToComsumptionRecord(Long profileId, Long itemId, Integer quantity) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Subtract the quantity from the item's stock
        item.setStock(item.getStock() - quantity);

        // If the stock is zero, delete the item from the database
        if (item.getStock() == 0) {
            itemRepository.delete(item);
            return;
        }

        ConsumptionRecord consumptionRecord = new ConsumptionRecord();
        consumptionRecord.setItem(item);
        consumptionRecord.setQuantity(quantity);
        consumptionRecord.setConsumedAt(LocalDateTime.now());
        consumptionRecord.setProfile(profile);

// Save the updated item and the new consumption record
        itemRepository.save(item);
        consumptionRecordRepository.save(consumptionRecord);
    }

    // Requirement 2: Get all consumption records by user
    public List<ConsumptionRecord> getAllConsumptionRecordsByUser(User user) {
        return this.consumptionRecordRepository.findAllByUser(user);
    }

    // Requirement 2: Get all consumption records by profile
    public List<ConsumptionRecord> getAllConsumptionRecordsByProfile(Long userId) {
        return this.consumptionRecordRepository.findAllByProfile(userId);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteConsumptionItemOver7Days() {
        List<ConsumptionRecord> consumptionRecords = consumptionRecordRepository.findAll();
        for (ConsumptionRecord consumptionRecord : consumptionRecords) {
            if (consumptionRecord.getConsumedAt().isBefore(LocalDateTime.now().minusDays(7))) {
                consumptionRecordRepository.delete(consumptionRecord);
            }
        }
    }
}
