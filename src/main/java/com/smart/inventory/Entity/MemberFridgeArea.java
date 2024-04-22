package com.smart.inventory.Entity;

import jakarta.persistence.*;

@Entity
public class MemberFridgeArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Profile profile;

    @ManyToOne
    private Fridge fridge;





}
