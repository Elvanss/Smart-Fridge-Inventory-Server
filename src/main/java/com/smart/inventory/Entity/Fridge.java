package com.smart.inventory.Entity;


import jakarta.persistence.*;
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
@Table(name = "fridge")
public class Fridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;


    @OneToMany
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private List<MemberFridgeArea> memberFridgeAreas = new ArrayList<>();


//    ProfileID (Foreign Key referencing UserProfiles.ProfileID)
//    Location (e.g., kitchen, basement)
//    Capacity

}