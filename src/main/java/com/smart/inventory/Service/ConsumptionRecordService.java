package com.smart.inventory.Service;

import com.smart.inventory.Entity.*;
import com.smart.inventory.Repository.ConsumptionRecordRepository;
import com.smart.inventory.Repository.ItemRepository;
import com.smart.inventory.Repository.ProfileRepository;
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
    public void transferItemToComsumptionRecord(Long fromProfileId, Long toProfileId, Long itemId, Integer quantity) {
        Profile fromProfile = profileRepository.findById(fromProfileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Profile toProfile = profileRepository.findById(toProfileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        FridgeInventory fromFridgeInventory = fromProfile.getFridgeInventory();
        if (fromFridgeInventory == null) {
            throw new RuntimeException("FridgeInventory not found");
        }

        Item item = fromFridgeInventory.getItems().stream() // get all items in the fridge
                .filter(i -> i.getId().equals(itemId))// filter the item by id
                .findFirst() // get the first item
                .orElseThrow(() -> new RuntimeException("Item not found"));

        ConsumptionRecord consumptionRecord = new ConsumptionRecord();
        consumptionRecord.setItem(item);
        consumptionRecord.setQuantity(quantity);
        consumptionRecord.setConsumedAt(LocalDateTime.now());
        consumptionRecord.setProfile(toProfile);
        toProfile.transferItemToConsumptionRecord(item, quantity);
        itemRepository.save(item);
        this.consumptionRecordRepository.save(consumptionRecord);
    }

    // Requirement 2: Get all consumption records by user
    public List<ConsumptionRecord> getAllConsumptionRecordsByUser(User user) {
        return this.consumptionRecordRepository.findAllByUser(user);
    }

    // Requirement 2: Get all consumption records by profile
    public List<ConsumptionRecord> getAllConsumptionRecordsByProfile(Long userId) {
        return this.consumptionRecordRepository.findAllByProfile(userId);
    }
}
