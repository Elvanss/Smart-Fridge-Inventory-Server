package com.smart.inventory.Repository;

import com.smart.inventory.Entity.FridgeInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeInventoryRepository extends JpaRepository<FridgeInventory, Long> {

}
