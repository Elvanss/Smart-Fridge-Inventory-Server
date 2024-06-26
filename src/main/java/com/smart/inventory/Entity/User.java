package com.smart.inventory.Entity;

import com.smart.inventory.Entity.Type.RoleList;
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// User.java
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private RoleList type;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

    @ManyToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ShoppingList> shoppingLists = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private SharedFridge sharedFridge;

    public List<FridgeInventory> getAllFridgeInventoriesInSharedFridge() {
        return sharedFridge.getFridgeInventories();
    }

    // Add Profile
    public void addProfile(Profile profile) {
        this.profiles.add(profile);
        profile.setUser(this);
    }

    // Remove Profile
    public void removeProfile(Profile profile) {
        this.profiles.remove(profile);
        profile.setUser(null);
    }

    // Get Number of Profiles
    public int getNumberOfProfiles() {
        return profiles.size();
    }

    // Add Shopping List
    public void addShoppingList(ShoppingList shoppingList) {
        shoppingList.getUser().add(this);
        this.shoppingLists.add(shoppingList);

    }

    // Remove Shopping List
    public void removeShoppingList(ShoppingList shoppingList) {
        this.shoppingLists.remove(shoppingList);
        shoppingList.getUser().remove(this);
    }

    // Get Number of Shopping Lists
    public int getNumberOfShoppingLists() {
        return shoppingLists.size();
    }
}
