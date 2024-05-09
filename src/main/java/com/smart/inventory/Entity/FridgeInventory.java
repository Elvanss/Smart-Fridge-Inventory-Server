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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Fridge")
public class FridgeInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fridge", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "fridge")
    private List<Item> item = new ArrayList<>();

    @OneToOne
    @NotNull(message = "User is required")
    private User user;

    // The size of items in number
    private Integer numberOfItems() {
        if (this.item == null) {
            return 0;
        } else if (this.item.size() > 300) {
            throw new RuntimeException("Fridge is full");
        }
        return this.item.size();
    }

    public void addItem(Item item) {
        item.setFridge(this);
        this.item.add(item);
    }

    public void removeItem(Item itemToBeAssigned) {
        itemToBeAssigned.setFridge(null);
        this.item.remove(itemToBeAssigned);
    }

    public void removeItems() {
        this.item.forEach(item -> item.setFridge(null));
        this.item = new ArrayList<>();
    }

}