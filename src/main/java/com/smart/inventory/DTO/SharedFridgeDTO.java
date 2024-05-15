package com.smart.inventory.DTO;

import com.smart.inventory.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SharedFridgeDTO {
    private Long id;
    private UserDTO userDTO;
    private int numberOfFridgeInventories;
}
