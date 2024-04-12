package com.smart.inventory.System;

import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.Type.DietaryList;
import com.smart.inventory.Entity.Type.RoleList;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Repository.ProfileRepository;
import com.smart.inventory.Repository.UserRepository;
import com.smart.inventory.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DBInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final UserService userService;

    public DBInitializer(UserRepository userRepository,
                         ProfileRepository profileRepository,
                         UserService userService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.userService = userService;
    }


    @Override
    public void run(String... args) {
        // Create Users
        User user1 = new User();
        user1.setUsername("adminUser");
        user1.setPassword("pass1");
        user1.setEmail("admin@gmail.com");
        user1.setRoleUsers(RoleList.ADMIN);

        User user2 = new User();
        user2.setUsername("account1");
        user2.setPassword("pass1");
        user2.setEmail("user@gmail.com");
        user2.setRoleUsers(RoleList.USER);

        // Create Profile
        Profile profile11 = new Profile();
        profile11.setName("AdminProfile");
        profile11.setAge(30);
        profile11.setDietary(DietaryList.BREAKFAST_CEREALS);
        profile11.setAllergies("None");
        profile11.setDescription("Admin Profile");

        Profile profile21 = new Profile();
        profile21.setName("Dad");
        profile21.setAge(40);
        profile21.setDietary(DietaryList.DAIRY_FREE);
        profile21.setAllergies("None");
        profile21.setDescription("Dad Profile");

        Profile profile22 = new Profile();
        profile22.setName("Mom");
        profile22.setAge(35);
        profile22.setDietary(DietaryList.ALCOHOL_FREE);
        profile22.setAllergies("None");
        profile22.setDescription("Mom Profile");

        // Add Profile to User
        user1.addProfile(profile11);
        user2.addProfile(profile21);
        user2.addProfile(profile22);

        // Save to DB
        userService.save(user1);
        userService.save(user2);

        profileRepository.save(profile11);
        profileRepository.save(profile21);
        profileRepository.save(profile22);

        // Print to console
        log.info("Users: {}", userRepository.findAll());
        log.info("Profiles: {}", profileRepository.findAll());
    }
}
