package com.smart.inventory.Mapper;

import com.smart.inventory.DTO.SharedFridgeDTO;
import com.smart.inventory.Entity.SharedFridge;
import org.springframework.stereotype.Component;

@Component
public class ShareFridgeMapper {

    private final UserMapper userMapper;

    public ShareFridgeMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    // ShardFridge to SharedFridgeDTO
    public SharedFridgeDTO toSharedFridgeDTO(SharedFridge sharedFridge) {
        SharedFridgeDTO sharedFridgeDTO = new SharedFridgeDTO();
        sharedFridgeDTO.setId(sharedFridge.getId());
        sharedFridgeDTO.setUserDTO(sharedFridge.getUser() != null
                                        ? this.userMapper.convertToDto(sharedFridge.getUser())
                                        : null);
        sharedFridgeDTO.setNumberOfFridgeInventories(sharedFridge.getNumberOfFridgeInventories());
        return sharedFridgeDTO;
    }

    // SharedFridgeDTO to SharedFridge
    public SharedFridge toSharedFridge(SharedFridgeDTO sharedFridgeDTO) {
        SharedFridge sharedFridge = new SharedFridge();
        sharedFridge.setId(sharedFridgeDTO.getId());
        return sharedFridge;
    }
}
