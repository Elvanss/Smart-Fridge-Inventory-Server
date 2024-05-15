package com.smart.inventory.Repository;

import com.smart.inventory.Entity.FridgeInventory;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.SharedFridge;
import com.smart.inventory.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FridgeInventoryRepository extends JpaRepository<FridgeInventory, Long> {

    @Query("SELECT f FROM FridgeInventory f WHERE f.sharedFridge = ?1")
    List<FridgeInventory> findBySharedFridge(SharedFridge sharedFridge);

    @Query("SELECT f FROM FridgeInventory f WHERE f.profile = ?1")
    FridgeInventory findByProfile(Profile profile);

    @Query("SELECT f FROM FridgeInventory f WHERE f.profile = ?1 AND f.sharedFridge = ?2")
    FridgeInventory findByProfileAndSharedFridge(Profile profileValid, SharedFridge sharedFridge);
}
