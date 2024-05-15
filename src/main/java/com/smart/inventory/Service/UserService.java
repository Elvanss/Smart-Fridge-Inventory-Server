package com.smart.inventory.Service;

import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.Type.RoleList;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Repository.FridgeInventoryRepository;
import com.smart.inventory.Repository.ProfileRepository;
import com.smart.inventory.Repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final FridgeInventoryRepository fridgeRepository;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ProfileRepository profileRepository, FridgeInventoryRepository fridgeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
        this.fridgeRepository = fridgeRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
    }

    public User save(User user) {
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setType(user.getType());
        return this.userRepository.save(user);
    }

    public User update(Long userId, User update) {
        User oldUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
        oldUser.setUsername(update.getUsername());
        oldUser.setEmail(update.getEmail());
        oldUser.setPassword(this.passwordEncoder.encode(update.getPassword()));
        return this.userRepository.save(oldUser);
    }

    public void delete(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
        // Delete all Fridge entities that reference the User entity
        this.fridgeRepository.deleteByUser(user);
        this.userRepository.deleteById(userId);
    }

    public Profile assignProfileToUser(Long userId, Long profileId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found!", userId));

        Profile newProfileToBeAssigned = this.profileRepository.findById(profileId)
                .orElseThrow(() -> new ObjectNotFoundException("Profile not found!", profileId));

        if (newProfileToBeAssigned.getUser() != null) {
            newProfileToBeAssigned.getUser().removeProfile(newProfileToBeAssigned);
        }

        user.addProfile(newProfileToBeAssigned);
        return this.profileRepository.save(newProfileToBeAssigned);

    }

    // make user sign up

}
