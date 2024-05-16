package com.smart.inventory.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingList extends JpaRepository<ShoppingList, Long> {
}
