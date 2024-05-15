package com.smart.inventory.Repository;

import com.smart.inventory.Entity.FridgeInventory;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FridgeInventoryRepository extends JpaRepository<FridgeInventory, Long> {

    @Query("SELECT f FROM FridgeInventory f WHERE f.user = ?1")
    void deleteByUser(User user);

    @Query("SELECT f FROM FridgeInventory f WHERE f.user.profiles = ?1")
    void deleteByProfile(Profile profile);
}
