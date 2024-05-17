package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.ConsumptionRecordDTO;
import com.smart.inventory.Entity.ConsumptionRecord;
import org.springframework.stereotype.Component;

@Component
public class NutritonTargetMapper {
    private final ProfileMapper profileMapper;
    private final ItemMapper itemMapper;

    public NutritonTargetMapper(ProfileMapper profileMapper, ItemMapper itemMapper) {
        this.profileMapper = profileMapper;
        this.itemMapper = itemMapper;
    }

    public ConsumptionRecordDTO toNutritionTargetDto(ConsumptionRecord consumptionRecord) {
        ConsumptionRecordDTO consumptionRecordDTO = new ConsumptionRecordDTO();
        consumptionRecordDTO.setId(consumptionRecord.getId());
        consumptionRecordDTO.setProfile(consumptionRecord.getProfile() != null
                ? this.profileMapper.convertToDto(consumptionRecord.getProfile())
                : null);
        consumptionRecordDTO.setItem(consumptionRecord.getItem() != null
                ? this.itemMapper.convertToItemDTO(consumptionRecord.getItem())
                : null);
        consumptionRecordDTO.setConsumedAt(consumptionRecord.getConsumedAt());
        return consumptionRecordDTO;
    }

    public ConsumptionRecord toNutritionTarget(ConsumptionRecordDTO consumptionRecordDTO) {
        ConsumptionRecord consumptionRecord = new ConsumptionRecord();
        consumptionRecord.setProfile(consumptionRecordDTO.getProfile() != null
                ? this.profileMapper.convertToEntity(consumptionRecordDTO.getProfile())
                : null);
        consumptionRecord.setItem(consumptionRecordDTO.getItem() != null
                ? this.itemMapper.convertToItem(consumptionRecordDTO.getItem())
                : null);
        consumptionRecord.setConsumedAt(consumptionRecordDTO.getConsumedAt());
        return consumptionRecord;
    }
}
