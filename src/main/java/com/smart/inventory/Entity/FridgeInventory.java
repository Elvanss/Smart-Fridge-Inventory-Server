package com.smart.inventory.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;


// FridgeInventory.java
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FridgeInventory")
public class FridgeInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shared_fridge_id", referencedColumnName = "id")
    private SharedFridge sharedFridge;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "fridgeInventory")
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        item.setFridgeInventory(this);
        this.items.add(item);
    }

    public void removeItem(Item itemToBeRemoved) {
        itemToBeRemoved.setFridgeInventory(null);
        this.items.remove(itemToBeRemoved);
    }

    public void removeItems() {
        this.items.forEach(item -> item.setFridgeInventory(null));
        this.items = new ArrayList<>();
    }

    public Integer getNumberOfItems() {
        return this.items.size();
    }
}