package com.smart.inventory.Repository;

import com.smart.inventory.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByName(String name);

    @Query("SELECT i FROM Item i WHERE i.name LIKE %?1%")
    List<Item> findByNameContaining(String name);
}
