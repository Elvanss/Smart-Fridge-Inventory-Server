package com.smart.inventory.Service;

import com.smart.inventory.Entity.ConsumptionRecord;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Repository.ConsumptionRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionRecordService {

    private final ConsumptionRecordRepository consumptionRecordRepository;

    public ConsumptionRecordService(ConsumptionRecordRepository consumptionRecordRepository) {
        this.consumptionRecordRepository = consumptionRecordRepository;
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

    public Item transferItemToComsumptionRecord(Profile profile, Item item, Integer quantity) {
        ConsumptionRecord consumptionRecord = new ConsumptionRecord();
        consumptionRecord.setItem(item);
        consumptionRecord.setQuantity(quantity);
        profile.transferItemToConsumptionRecord(item, quantity);
        return this.consumptionRecordRepository.save(consumptionRecord).getItem();
    }

    // Requirement 2: Get all consumption records by user
    public List<ConsumptionRecord> getAllConsumptionRecordsByUser(Long userId) {
        return this.consumptionRecordRepository.findAllByUser(userId);
    }

    // Requirement 2: Get all consumption records by profile
    public List<ConsumptionRecord> getAllConsumptionRecordsByProfile(Long userId) {
        return this.consumptionRecordRepository.findAllByProfile(userId);
    }
}
