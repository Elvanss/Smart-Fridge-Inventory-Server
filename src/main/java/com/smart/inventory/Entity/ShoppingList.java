package com.smart.inventory.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "item")
    @Column(name = "quantity")
    private Map<String, Integer> items = new HashMap<>();

    @Column(name = "description")
    private String description;


}
