package com.smart.inventory.Entity;

import com.smart.inventory.Entity.Type.StockStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


// Item.java
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "fat")
    private Double fat;

    @Column(name = "stock_status")
    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "days_left")
    private Long daysLeft;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fridge_inventory_id", referencedColumnName = "id")
    private FridgeInventory fridgeInventory;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsumptionRecord> consumptionRecords = new ArrayList<>();

    public void addConsumptionRecord(ConsumptionRecord consumptionRecord) {
        consumptionRecords.add(consumptionRecord);
        consumptionRecord.setItem(this);
    }

    public void removeConsumptionRecord(ConsumptionRecord consumptionRecord) {
        consumptionRecords.remove(consumptionRecord);
        consumptionRecord.setItem(null);
    }

    public void removeAllConsumptionRecords() {
        consumptionRecords.forEach(consumptionRecord -> consumptionRecord.setItem(null));
        consumptionRecords.clear();
    }

    public Integer getNumberOfConsumptionRecords() {
        return this.consumptionRecords.size();
    }


}