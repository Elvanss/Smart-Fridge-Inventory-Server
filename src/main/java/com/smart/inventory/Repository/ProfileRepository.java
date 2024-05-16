package com.smart.inventory.Repository;

import com.smart.inventory.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.user.id = ?1")
    Optional<Profile> findByUserId(Long userId);

    @Query("SELECT p FROM Profile p WHERE p.user.id = ?1 AND p.id = ?2")
    Profile findProfileByUserId(Long userId, Long profileId);
}
