package com.smart.inventory.Service;

import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.SharedFridge;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Repository.ProfileRepository;
import com.smart.inventory.Repository.SharedFridgeRepository;
import com.smart.inventory.Repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final SharedFridgeRepository sharedFridgeInventory;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ProfileRepository profileRepository,
                       SharedFridgeRepository sharedFridgeInventory) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.profileRepository = profileRepository;
        this.sharedFridgeInventory = sharedFridgeInventory;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
    }

    // Sign UpzZZZZ
    public User save(User user) {
        user = this.userRepository.save(user);
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setType(user.getType());
        createSharedFridge(user);
        return user;
    }

    public User saveInit(User user) {
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setType(user.getType());
        return this.userRepository.save(user);
    }

    private void createSharedFridge(User user) {
        SharedFridge sharedFridge = new SharedFridge();
        sharedFridge.setUser(user);
        user.setSharedFridge(sharedFridge);
        sharedFridgeInventory.save(sharedFridge);
    }

    public User update(Long userId, User update) {
        User oldUser = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found!", userId));
        oldUser.setUsername(update.getUsername());
        oldUser.setEmail(update.getEmail());
        oldUser.setPassword(passwordEncoder.encode(update.getPassword()));
        return userRepository.save(oldUser);
    }

    public void delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found!", userId));
        sharedFridgeInventory.deleteByUser(user);
        userRepository.deleteById(userId);
    }

    public void assignProfileToUser(Long userId, Long profileId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found!", userId));

        Profile newProfileToBeAssigned = profileRepository.findById(profileId)
                .orElseThrow(() -> new ObjectNotFoundException("Profile not found!", profileId));

        if (newProfileToBeAssigned.getUser() != null) {
            newProfileToBeAssigned.getUser().removeProfile(newProfileToBeAssigned);
        }

        user.addProfile(newProfileToBeAssigned);
        profileRepository.save(newProfileToBeAssigned);
    }


}
