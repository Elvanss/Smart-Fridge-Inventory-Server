package com.smart.inventory.Repository;

import com.smart.inventory.Entity.SharedFridge;
import com.smart.inventory.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SharedFridgeRepository extends JpaRepository<SharedFridge, Long>{

    @Query("SELECT s FROM SharedFridge s WHERE s.user = ?1")
    void deleteByUser(User user);
}
