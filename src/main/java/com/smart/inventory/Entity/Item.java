package com.smart.inventory.Entity;

import com.smart.inventory.Entity.Type.StockStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "stock_status")
    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "days_left")
    private Integer daysLeft;

    @ManyToOne
    @JoinColumn(name = "member_fridge_area_id", referencedColumnName = "id")
    private MemberFridgeArea memberFridgeArea;

}