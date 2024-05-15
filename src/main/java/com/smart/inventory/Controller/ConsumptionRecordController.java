package com.smart.inventory.Controller;

import com.smart.inventory.DTO.ConsumptionRecordDTO;
import com.smart.inventory.Entity.ConsumptionRecord;
import com.smart.inventory.Mapper.ConsumptionRecordMapper;
import com.smart.inventory.Service.ConsumptionRecordService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/consumption-records")
public class ConsumptionRecordController {

    private final ConsumptionRecordService consumptionRecordService;
    private final ConsumptionRecordMapper consumptionRecordMapper;

    public ConsumptionRecordController(ConsumptionRecordService consumptionRecordService, ConsumptionRecordMapper consumptionRecordMapper) {
        this.consumptionRecordService = consumptionRecordService;
        this.consumptionRecordMapper = consumptionRecordMapper;
    }


    @GetMapping("/profile")
    public Result getAllConsumptionRecordsByProfile(@RequestParam("profile") Long profileId) {
        List<ConsumptionRecord> consumptionRecords = this.consumptionRecordService.getAllConsumptionRecordsByProfile(profileId);
        List<ConsumptionRecordDTO> consumptionRecordDTOs = consumptionRecords.stream()
                .map(this.consumptionRecordMapper::toConsumptionRecordDTO)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Success", consumptionRecordDTOs);
    }

    @GetMapping("/user")
    public Result getAllConsumptionRecordsByUser(@RequestParam("user") Long userId) {
        List<ConsumptionRecord> consumptionRecords = this.consumptionRecordService.getAllConsumptionRecordsByUser(userId);
        List<ConsumptionRecordDTO> consumptionRecordDTOs = consumptionRecords.stream()
                .map(this.consumptionRecordMapper::toConsumptionRecordDTO)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Success", consumptionRecordDTOs);
    }
}
