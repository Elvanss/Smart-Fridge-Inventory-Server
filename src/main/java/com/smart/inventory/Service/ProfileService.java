package com.smart.inventory.Service;

import com.smart.inventory.Entity.User;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Repository.FridgeInventoryRepository;
import com.smart.inventory.Repository.ProfileRepository;
import com.smart.inventory.Repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final FridgeInventoryRepository fridgeRepository;

    public ProfileService(ProfileRepository profileRepository,
                          UserRepository userRepository,
                          FridgeInventoryRepository fridgeRepository) {

        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.fridgeRepository = fridgeRepository;
    }

    // Create Profile
    public Profile createProfile(Long userId, Profile profile) {
        profile.setName(profile.getName());
        profile.setAge(profile.getAge());
        profile.setDietary(profile.getDietary());
        profile.setAllergies(profile.getAllergies());
        profile.setDescription(profile.getDescription());
        return this.profileRepository.save(profile);
    }

    // Updated Profile
    public Profile updatedProfile(Long profileId, Profile updatedProfile) {
    Profile profileIn = this.profileRepository.findById(profileId)
            .orElseThrow(() -> new ObjectNotFoundException("Profile not found with id: ", profileId));
    profileIn.setName(updatedProfile.getName());
    profileIn.setAge(updatedProfile.getAge());
    profileIn.setDietary(updatedProfile.getDietary());
    profileIn.setAllergies(updatedProfile.getAllergies());
    profileIn.setDescription(updatedProfile.getDescription());

    return this.profileRepository.save(profileIn);
}

    // Get Profile by Id
    public Profile getProfileById(Long id) {
        return this.profileRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Profile not found with id: ", id));
    }

    // Get All Profile
    public List<Profile> getAllProfilesForUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("User not found with id: ", id));
        return user.getProfiles();
    }

    @Transactional
    public void delete(Long profileId) {
        Profile profile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new ObjectNotFoundException("Profile Not Found!", profileId));

        // Delete all Fridge entities that reference the Profile entity
        this.fridgeRepository.deleteByProfile(profile);

        // Now you can delete the Profile entity
        this.profileRepository.deleteById(profileId);
    }
}
