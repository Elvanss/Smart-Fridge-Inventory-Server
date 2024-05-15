package com.smart.inventory.Repository;

import com.smart.inventory.Entity.ConsumptionRecord;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumptionRecordRepository extends JpaRepository<ConsumptionRecord, Long> {

//    @Query("SELECT c FROM ConsumptionRecord c WHERE c.profile.id = ?1")
//    List<ConsumptionRecord> findByProfile(Long profileId);

    @Query("SELECT c FROM ConsumptionRecord c WHERE c.profile.id = ?1")
    List<ConsumptionRecord> findAllByProfile(Long profileId);

    @Query("SELECT c FROM ConsumptionRecord c WHERE c.profile.user = ?1")
    List<ConsumptionRecord> findAllByUser(Long userId);
}
