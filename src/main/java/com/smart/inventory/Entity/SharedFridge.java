package com.smart.inventory.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// SharedFridge.java
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Fridge")
public class SharedFridge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sharedFridge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FridgeInventory> fridgeInventories = new ArrayList<>();

    public void addFridgeInventory(FridgeInventory fridgeInventory) {
        this.fridgeInventories.add(fridgeInventory);
        fridgeInventory.setSharedFridge(this);
    }

    public void removeFridgeInventory(FridgeInventory fridgeInventory) {
        this.fridgeInventories.remove(fridgeInventory);
        fridgeInventory.setSharedFridge(null);
    }

    public int getNumberOfFridgeInventories() {
        return fridgeInventories.size();
    }
}
