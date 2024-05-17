package com.smart.inventory.Service;

import com.smart.inventory.Entity.*;
import com.smart.inventory.Repository.FridgeInventoryRepository;
import com.smart.inventory.Repository.ProfileRepository;
import com.smart.inventory.Repository.SharedFridgeRepository;
import com.smart.inventory.Repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final FridgeInventoryRepository fridgeInventoryRepository;
    private final UserService userService;
    private final SharedFridgeRepository sharedFridgeRepository;

    public ProfileService(ProfileRepository profileRepository,
                          UserRepository userRepository,
                          FridgeInventoryRepository fridgeInventoryRepository,
                          UserService userService,
                          SharedFridgeRepository sharedFridgeRepository) {

        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.fridgeInventoryRepository = fridgeInventoryRepository;
        this.userService = userService;
        this.sharedFridgeRepository = sharedFridgeRepository;
    }

    public Profile createProfile(Long userId, Profile profile) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id: ", userId));
        profile = this.profileRepository.save(profile);
        profile.setName(profile.getName());
        profile.setAge(profile.getAge());
        profile.setDietary(profile.getDietary());
        profile.setAllergies(profile.getAllergies());
        profile.setDescription(profile.getDescription());

        // Create a new FridgeInventory and set it to the profile
        FridgeInventory fridgeInventory = new FridgeInventory();
        fridgeInventory.setProfile(profile);

        SharedFridge sharedFridge = user.getSharedFridge();
        if (sharedFridge == null) {
            sharedFridge = new SharedFridge();
            sharedFridge = sharedFridgeRepository.save(sharedFridge);
            user.setSharedFridge(sharedFridge);
            userRepository.save(user);
        }
        fridgeInventory.setSharedFridge(sharedFridge);
        profile.setFridgeInventory(fridgeInventory);

        // Assign the created profile to the user
        userService.assignProfileToUser(user.getId(), profile.getId());
        fridgeInventory = fridgeInventoryRepository.save(fridgeInventory);
        return profile;
    }

//    private void createFridgeInventory(User user, Profile profile) {
//        FridgeInventory fridgeInventory = fridgeInventoryRepository.findById(profile.getFridgeInventory().getId())
//            .orElseThrow(() -> new RuntimeException("FridgeInventory not found"));
//
//        // Fetch the SharedFridge from the database to ensure it's managed
//        SharedFridge sharedFridge = sharedFridgeRepository.findById(user.getSharedFridge().getId())
//            .orElseThrow(() -> new RuntimeException("SharedFridge not found"));
//
//        fridgeInventory.setSharedFridge(sharedFridge);
//        fridgeInventory.setProfile(profile);
//        fridgeInventoryRepository.save(fridgeInventory);
//    }

    public List<Profile> getProfilesForUser(User user) {
        return user.getProfiles();
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

    // Delete Profile
    @Transactional
    public void delete(Long profileId) {
        Profile profile = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new ObjectNotFoundException("Profile Not Found!", profileId));
        profile.getUser().removeProfile(profile);
        this.profileRepository.deleteById(profileId);
    }

    public Profile getProfileByUserId(Long userId, Long profileId) {
        return this.profileRepository.findProfileByUserId(userId, profileId);
    }

}
