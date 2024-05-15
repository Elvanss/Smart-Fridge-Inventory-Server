package com.smart.inventory.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionRecordDTO {
    private Long id;
    private ProfileDTO profile;
    private ItemDTO item;
    private LocalDateTime consumedAt;
}
