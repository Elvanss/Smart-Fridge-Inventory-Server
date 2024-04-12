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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

    @Column(name = "role")
    private RoleList roleUsers;

    /**
     * Other Resolved Methods for User Entity
     */

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



}
