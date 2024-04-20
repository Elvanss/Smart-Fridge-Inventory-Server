package com.smart.inventory.Service;

import com.smart.inventory.Entity.Type.RoleList;
import com.smart.inventory.Entity.User;
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


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
    }

    public User save(User user, RoleList role) {
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setType(role);
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
        this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user not found!", userId));
        this.userRepository.deleteById(userId);
    }

    // make user sign up

}
