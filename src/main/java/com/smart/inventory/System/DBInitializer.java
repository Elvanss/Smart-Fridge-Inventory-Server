package com.smart.inventory.System;

import com.smart.inventory.Entity.FridgeInventory;
import com.smart.inventory.Entity.Item;
import com.smart.inventory.Entity.Profile;
import com.smart.inventory.Entity.Type.DietaryList;
import com.smart.inventory.Entity.Type.RoleList;
import com.smart.inventory.Entity.Type.StockStatus;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Repository.FridgeInventoryRepository;
import com.smart.inventory.Repository.ItemRepository;
import com.smart.inventory.Repository.ProfileRepository;
import com.smart.inventory.Repository.UserRepository;
import com.smart.inventory.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class DBInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final FridgeInventoryRepository fridgeInventoryRepository;
    private final UserService userService;
    private final ItemRepository itemRepository;

    public DBInitializer(UserRepository userRepository,
                         ProfileRepository profileRepository,
                         FridgeInventoryRepository fridgeInventoryRepository,
                         UserService userService, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.fridgeInventoryRepository = fridgeInventoryRepository;
        this.userService = userService;
        this.itemRepository = itemRepository;
    }


    @Override
    public void run(String... args) {
        // Create Users
        User user1 = new User();
        user1.setUsername("adminUser");
        user1.setPassword("pass1");
        user1.setEmail("admin@gmail.com");
        user1.setType(RoleList.ADMIN);

        User user2 = new User();
        user2.setUsername("account1");
        user2.setPassword("pass1");
        user2.setEmail("user@gmail.com");
        user2.setType(RoleList.USER);

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


        // Create items
        Item item1 = new Item();
        item1.setName("Milk");
        item1.setCategory("Dairy");
        item1.setStock(1);
        item1.setCalories(100.4);
        item1.setStockStatus(StockStatus.IN_STOCK);
        item1.setPurchaseDate(LocalDate.of(2021, 10, 1));
        item1.setExpiryDate(LocalDate.of(2021, 10, 31));
        // Calculate days left
//        if (item1.getExpiryDate() != null) {
//            item1.setDaysLeft(item1.getExpiryDate().toEpochDay() - item1.getPurchaseDate().toEpochDay());
//        }
        item1.setDescription("Fresh Milk");
//        Item itemSaved = itemRepository.save(item1);



        // Create Fridges Inventory for User
        FridgeInventory fridgeInventory2 = new FridgeInventory();
        fridgeInventory2.setLocation("Kitchen");
        fridgeInventory2.setCapacity(100);
        fridgeInventory2.setUser(user2);
        fridgeInventory2.addItem(item1);


        // Save to DB
        fridgeInventoryRepository.save(fridgeInventory2);


        // Print to console
        log.info("Users: {}", userRepository.findAll());
        log.info("Profiles: {}", profileRepository.findAll());
    }
}
