package com.smart.inventory.DTO;

import com.smart.inventory.Entity.Type.DietaryList;

public record ProfileDTO (Long id,
                          String name,
                          Integer age,
                          DietaryList dietary,
                          String allergies,
                          String description,
                          Long user_id) {
}
