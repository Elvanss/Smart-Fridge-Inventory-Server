package com.smart.inventory.Controller;

import com.smart.inventory.DTO.ConsumptionRecordDTO;
import com.smart.inventory.Entity.ConsumptionRecord;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Mapper.ConsumptionRecordMapper;
import com.smart.inventory.Service.ConsumptionRecordService;
import com.smart.inventory.Service.UserService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/consumption-records")
public class ConsumptionRecordController {

    private final ConsumptionRecordService consumptionRecordService;
    private final ConsumptionRecordMapper consumptionRecordMapper;
    private final UserService userService;

    public ConsumptionRecordController(ConsumptionRecordService consumptionRecordService,
                                       ConsumptionRecordMapper consumptionRecordMapper,
                                       UserService userService) {
        this.consumptionRecordService = consumptionRecordService;
        this.consumptionRecordMapper = consumptionRecordMapper;
        this.userService = userService;
    }

    // Requirement 4: Get all consumption records by profile
    @GetMapping
    public Result getAllConsumptionRecordsByProfile(@RequestParam("profile") Long profileId) {
        List<ConsumptionRecord> consumptionRecords = this.consumptionRecordService.getAllConsumptionRecordsByProfile(profileId);
        List<ConsumptionRecordDTO> consumptionRecordDTOs = consumptionRecords.stream()
                .map(this.consumptionRecordMapper::toConsumptionRecordDTO)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Success", consumptionRecordDTOs);
    }

    // Requirement 4: Get all consumption records by user
@GetMapping("/user")
public Result getAllConsumptionRecordsByUser(@RequestParam("user") Long userId) {
    User user = userService.findById(userId); // Fetch the User
    List<ConsumptionRecord> consumptionRecords = this.consumptionRecordService.getAllConsumptionRecordsByUser(user); // Pass the User to the service method
    List<ConsumptionRecordDTO> consumptionRecordDTOs = consumptionRecords.stream()
            .map(this.consumptionRecordMapper::toConsumptionRecordDTO)
            .toList();
    return new Result(true, StatusCode.SUCCESS, "Success", consumptionRecordDTOs);
}

    @PostMapping("/transfer")
    public Result transferItemToComsumptionRecord(@RequestParam("profileId") Long ProfileId,
                                                  @RequestParam("itemId") Long itemId,
                                                  @RequestParam("quantity") Integer quantity) {
        this.consumptionRecordService.transferItemToComsumptionRecord(ProfileId, itemId, quantity);
        return new Result(true, StatusCode.SUCCESS, "Item transferred");
    }
}
