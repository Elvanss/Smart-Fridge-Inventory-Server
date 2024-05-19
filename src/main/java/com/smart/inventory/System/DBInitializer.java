package com.smart.inventory.System;

import com.smart.inventory.Entity.*;
import com.smart.inventory.Entity.Type.DietaryList;
import com.smart.inventory.Entity.Type.RoleList;
import com.smart.inventory.Entity.Type.StockStatus;
import com.smart.inventory.Repository.*;
import com.smart.inventory.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@Slf4j
public class DBInitializer implements CommandLineRunner {


    private final UserService userService;
    private final MealRepository mealRepository;
    private final SharedFridgeRepository sharedFridgeInventory;
    private final ShoppingListRepository shoppingListRepository;
    private final UserRepository userRepository;


    public DBInitializer(UserService userService,
                         MealRepository mealRepository,
                         SharedFridgeRepository sharedFridgeInventory,
                         ShoppingListRepository shoppingListRepository, UserRepository userRepository) {
        this.userService = userService;
        this.mealRepository = mealRepository;
        this.sharedFridgeInventory = sharedFridgeInventory;
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) {
        /*
         * Data Initialization in Workflow:
         * 1 User
         * 1 sharedFridge
         * 3 Profile
         * 3 FridgeInventory
         * 18 Items
         * 100 Meals
         * 9 Consumption History
         * */



        // Initialize Profile
        Profile profile = new Profile();
        profile.setName("Profile");
        profile.setAge(30);
        profile.setDietary(DietaryList.BREAKFAST_CEREALS);
        profile.setAllergies("None");
        profile.setDescription("Profile Description");

        Profile profile2 = new Profile();
        profile2.setName("Profile2");
        profile2.setAge(40);
        profile2.setDietary(DietaryList.DAIRY_FREE);
        profile2.setAllergies("None");
        profile2.setDescription("Profile2 Description");

        Profile profile3 = new Profile();
        profile3.setName("Profile3");
        profile3.setAge(50);
        profile3.setDietary(DietaryList.GLUTEN_FREE);
        profile3.setAllergies("None");
        profile3.setDescription("Profile3 Description");

        // Initialize User
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@example.com");
        user.setPassword("password");
        user.setType(RoleList.USER);
        user.addProfile(profile);

        user.addProfile(profile2);
        user.addProfile(profile3);

        // Initialize Shared Fridge
        Item item1 = new Item();
        item1.setName("Milk");
        item1.setCategory("Dairy");
        item1.setStock(11);
        item1.setCalories(100.4);
        item1.setProtein(8.0);
        item1.setFat(2.0);
        item1.setStockStatus(StockStatus.IN_STOCK);
        item1.setPurchaseDate(LocalDate.of(2021, 10, 1));
        item1.setExpiryDate(LocalDate.of(2021, 10, 31));
        item1.setDaysLeft(item1.getExpiryDate().toEpochDay() - item1.getPurchaseDate().toEpochDay());
        item1.setDescription("Fresh Milk");

        Item item2 = new Item();
        item2.setName("Bread");
        item2.setCategory("Bakery");
        item2.setStock(5);
        item2.setCalories(250.0);
        item2.setProtein(25.2);
        item2.setFat(3.0);
        item2.setStockStatus(StockStatus.IN_STOCK);
        item2.setPurchaseDate(LocalDate.of(2021, 10, 2));
        item2.setExpiryDate(LocalDate.of(2021, 10, 7));
        item2.setDaysLeft(item2.getExpiryDate().toEpochDay() - item2.getPurchaseDate().toEpochDay());
        item2.setDescription("Whole grain bread");

        Item item3 = new Item();
        item3.setName("Apple");
        item3.setCategory("Fruit");
        item3.setStock(10);
        item3.setCalories(52.0);
        item3.setProtein(0.3);
        item3.setFat(0.2);
        item3.setStockStatus(StockStatus.IN_STOCK);
        item3.setPurchaseDate(LocalDate.of(2021, 10, 3));
        item3.setExpiryDate(LocalDate.of(2021, 10, 13));
        item3.setDaysLeft(item3.getExpiryDate().toEpochDay() - item3.getPurchaseDate().toEpochDay());
        item3.setDescription("Red apples");

        Item item4 = new Item();
        item4.setName("Eggs");
        item4.setCategory("Dairy");
        item4.setStock(12);
        item4.setCalories(68.0);
        item4.setProtein(5.5);
        item4.setFat(4.8);
        item4.setStockStatus(StockStatus.IN_STOCK);
        item4.setPurchaseDate(LocalDate.of(2021, 10, 4));
        item4.setExpiryDate(LocalDate.of(2021, 10, 18));
        item4.setDaysLeft(item4.getExpiryDate().toEpochDay() - item4.getPurchaseDate().toEpochDay());
        item4.setDescription("Free-range eggs");

        Item item5 = new Item();
        item5.setName("Cheese");
        item5.setCategory("Dairy");
        item5.setStock(11);
        item5.setCalories(402.0);
        item5.setProtein(25.0);
        item5.setFat(33.0);
        item5.setStockStatus(StockStatus.IN_STOCK);
        item5.setPurchaseDate(LocalDate.of(2021, 10, 5));
        item5.setExpiryDate(LocalDate.of(2021, 10, 20));
        item5.setDaysLeft(item5.getExpiryDate().toEpochDay() - item5.getPurchaseDate().toEpochDay());
        item5.setDescription("Cheddar cheese");

        Item item6 = new Item();
        item6.setName("Chicken");
        item6.setCategory("Meat");
        item6.setStock(11);
        item6.setCalories(335.0);
        item6.setProtein(31.0);
        item6.setFat(20.0);
        item6.setStockStatus(StockStatus.IN_STOCK);
        item6.setPurchaseDate(LocalDate.of(2021, 10, 6));
        item6.setExpiryDate(LocalDate.of(2021, 10, 9));
        item6.setDaysLeft(item6.getExpiryDate().toEpochDay() - item6.getPurchaseDate().toEpochDay());
        item6.setDescription("Chicken breast");

        Item item7 = new Item();
        item7.setName("Beef");
        item7.setCategory("Meat");
        item7.setStock(11);
        item7.setCalories(250.0);
        item7.setProtein(26.0);
        item7.setFat(17.0);
        item7.setStockStatus(StockStatus.IN_STOCK);
        item7.setPurchaseDate(LocalDate.of(2021, 10, 7));
        item7.setExpiryDate(LocalDate.of(2021, 10, 10));
        item7.setDaysLeft(item7.getExpiryDate().toEpochDay() - item7.getPurchaseDate().toEpochDay());
        item7.setDescription("Beef steak");

        Item item8 = new Item();
        item8.setName("Pork");
        item8.setCategory("Meat");
        item8.setStock(12);
        item8.setCalories(250.0);
        item8.setProtein(26.0);
        item8.setFat(17.0);
        item8.setStockStatus(StockStatus.IN_STOCK);
        item8.setPurchaseDate(LocalDate.of(2021, 10, 8));
        item8.setExpiryDate(LocalDate.of(2021, 10, 11));
        item8.setDaysLeft(item8.getExpiryDate().toEpochDay() - item8.getPurchaseDate().toEpochDay());
        item8.setDescription("Pork chop");

        Item item9 = new Item();
        item9.setName("Orange Juice");
        item9.setCategory("Beverages");
        item9.setStock(14);
        item9.setCalories(45.0);
        item9.setProtein(0.7);
        item9.setFat(0.2);
        item9.setStockStatus(StockStatus.IN_STOCK);
        item9.setPurchaseDate(LocalDate.of(2021, 10, 4));
        item9.setExpiryDate(LocalDate.of(2021, 11, 4));
        item9.setDaysLeft(item9.getExpiryDate().toEpochDay() - item9.getPurchaseDate().toEpochDay());
        item9.setDescription("100% pure orange juice");

        Item item10 = new Item();
        item10.setName("Yogurt");
        item10.setCategory("Dairy");
        item10.setStock(16);
        item10.setCalories(59.0);
        item10.setProtein(10.0);
        item10.setFat(0.4);
        item10.setStockStatus(StockStatus.IN_STOCK);
        item10.setPurchaseDate(LocalDate.of(2021, 10, 2));
        item10.setExpiryDate(LocalDate.of(2021, 10, 16));
        item10.setDaysLeft(item10.getExpiryDate().toEpochDay() - item10.getPurchaseDate().toEpochDay());
        item10.setDescription("Low-fat yogurt");

        Item item11 = new Item();
        item11.setName("Peanut Butter");
        item11.setCategory("Spreads");
        item11.setStock(12);
        item11.setCalories(588.0);
        item11.setProtein(25.0);
        item11.setFat(50.0);
        item11.setStockStatus(StockStatus.IN_STOCK);
        item11.setPurchaseDate(LocalDate.of(2021, 10, 5));
        item11.setExpiryDate(LocalDate.of(2022, 4, 5));
        item11.setDaysLeft(item11.getExpiryDate().toEpochDay() - item11.getPurchaseDate().toEpochDay());
        item11.setDescription("Creamy peanut butter");

        Item item12 = new Item();
        item12.setName("Salmon");
        item12.setCategory("Seafood");
        item12.setStock(14);
        item12.setCalories(208.0);
        item12.setProtein(20.0);
        item12.setFat(13.0);
        item12.setStockStatus(StockStatus.IN_STOCK);
        item12.setPurchaseDate(LocalDate.of(2021, 10, 6));
        item12.setExpiryDate(LocalDate.of(2021, 10, 12));
        item12.setDaysLeft(item12.getExpiryDate().toEpochDay() - item12.getPurchaseDate().toEpochDay());
        item12.setDescription("Fresh Atlantic salmon fillet");

        Item item13 = new Item();
        item13.setName("Spinach");
        item13.setCategory("Vegetables");
        item13.setStock(30);
        item13.setCalories(23.0);
        item13.setProtein(2.9);
        item13.setFat(0.4);
        item13.setStockStatus(StockStatus.IN_STOCK);
        item13.setPurchaseDate(LocalDate.of(2021, 10, 7));
        item13.setExpiryDate(LocalDate.of(2021, 10, 14));
        item13.setDaysLeft(item13.getExpiryDate().toEpochDay() - item13.getPurchaseDate().toEpochDay());
        item13.setDescription("Fresh spinach leaves");

        Item item14 = new Item();
        item14.setName("Greek Yogurt");
        item14.setCategory("Dairy");
        item14.setStock(6);
        item14.setCalories(59.0);
        item14.setProtein(10.0);
        item14.setFat(0.4);
        item14.setStockStatus(StockStatus.IN_STOCK);
        item14.setPurchaseDate(LocalDate.of(2021, 10, 8));
        item14.setExpiryDate(LocalDate.of(2021, 10, 22));
        item14.setDaysLeft(item14.getExpiryDate().toEpochDay() - item14.getPurchaseDate().toEpochDay());
        item14.setDescription("Low-fat Greek yogurt");

        Item item15 = new Item();
        item15.setName("Duck");
        item15.setCategory("Dairy");
        item15.setStock(12);
        item15.setCalories(155.0);
        item15.setProtein(13.0);
        item15.setFat(11.0);
        item15.setStockStatus(StockStatus.IN_STOCK);
        item15.setPurchaseDate(LocalDate.of(2021, 10, 9));
        item15.setExpiryDate(LocalDate.of(2021, 11, 9));
        item15.setDaysLeft(item15.getExpiryDate().toEpochDay() - item15.getPurchaseDate().toEpochDay());
        item15.setDescription("Fresh Duck breast");

        Item item16 = new Item();
        item16.setName("Almond Milk");
        item16.setCategory("Beverages");
        item16.setStock(7);
        item16.setCalories(30.0);
        item16.setProtein(1.0);
        item16.setFat(2.5);
        item16.setStockStatus(StockStatus.IN_STOCK);
        item16.setPurchaseDate(LocalDate.of(2021, 10, 10));
        item16.setExpiryDate(LocalDate.of(2021, 11, 10));
        item16.setDaysLeft(item16.getExpiryDate().toEpochDay() - item16.getPurchaseDate().toEpochDay());
        item16.setDescription("Unsweetened almond milk");

        Item item17 = new Item();
        item17.setName("Broccoli");
        item17.setCategory("Vegetables");
        item17.setStock(30);
        item17.setCalories(55.0);
        item17.setProtein(3.7);
        item17.setFat(0.6);
        item17.setStockStatus(StockStatus.IN_STOCK);
        item17.setPurchaseDate(LocalDate.of(2021, 10, 11));
        item17.setExpiryDate(LocalDate.of(2021, 10, 18));
        item17.setDaysLeft(item17.getExpiryDate().toEpochDay() - item17.getPurchaseDate().toEpochDay());
        item17.setDescription("Fresh broccoli florets");

        Item item18 = new Item();
        item18.setName("Strawberries");
        item18.setCategory("Fruits");
        item18.setStock(35);
        item18.setCalories(32.0);
        item18.setProtein(0.7);
        item18.setFat(0.3);
        item18.setStockStatus(StockStatus.IN_STOCK);
        item18.setPurchaseDate(LocalDate.of(2021, 10, 12));
        item18.setExpiryDate(LocalDate.of(2021, 10, 17));
        item18.setDaysLeft(item18.getExpiryDate().toEpochDay() - item18.getPurchaseDate().toEpochDay());
        item18.setDescription("Fresh organic strawberries");

        Item item19 = new Item();
        item19.setName("Peanut Butter");
        item19.setCategory("Spreads");
        item19.setStock(25);
        item19.setCalories(590.0);
        item19.setProtein(25.0);
        item19.setFat(49.0);
        item19.setStockStatus(StockStatus.IN_STOCK);
        item19.setPurchaseDate(LocalDate.of(2023, 5, 10));
        item19.setExpiryDate(LocalDate.of(2023, 8, 10));
        item19.setDaysLeft(item19.getExpiryDate().toEpochDay() - item19.getPurchaseDate().toEpochDay());
        item19.setDescription("Creamy peanut butter");

        Item item20 = new Item();
        item20.setName("Jelly");
        item20.setCategory("Spreads");
        item20.setStock(15);
        item20.setCalories(250.0);
        item20.setProtein(0.5);
        item20.setFat(0.1);
        item20.setStockStatus(StockStatus.IN_STOCK);
        item20.setPurchaseDate(LocalDate.of(2023, 5, 5));
        item20.setExpiryDate(LocalDate.of(2023, 7, 5));
        item20.setDaysLeft(item20.getExpiryDate().toEpochDay() - item20.getPurchaseDate().toEpochDay());
        item20.setDescription("Strawberry jelly");

        Item item21 = new Item();
        item21.setName("Broccoli");
        item21.setCategory("Vegetables");
        item21.setStock(20);
        item21.setCalories(34.0);
        item21.setProtein(2.6);
        item21.setFat(0.4);
        item21.setStockStatus(StockStatus.IN_STOCK);
        item21.setPurchaseDate(LocalDate.of(2023, 5, 12));
        item21.setExpiryDate(LocalDate.of(2023, 5, 19));
        item21.setDaysLeft(item21.getExpiryDate().toEpochDay() - item21.getPurchaseDate().toEpochDay());
        item21.setDescription("Fresh broccoli florets");

        Item item22 = new Item();
        item22.setName("Quinoa");
        item22.setCategory("Grains");
        item22.setStock(10);
        item22.setCalories(120.0);
        item22.setProtein(4.4);
        item22.setFat(1.9);
        item22.setStockStatus(StockStatus.IN_STOCK);
        item22.setPurchaseDate(LocalDate.of(2023, 4, 1));
        item22.setExpiryDate(LocalDate.of(2024, 4, 1));
        item22.setDaysLeft(item22.getExpiryDate().toEpochDay() - item22.getPurchaseDate().toEpochDay());
        item22.setDescription("Organic quinoa");

        Item item23 = new Item();
        item23.setName("Garlic");
        item23.setCategory("Vegetables");
        item23.setStock(30);
        item23.setCalories(149.0);
        item23.setProtein(6.4);
        item23.setFat(0.5);
        item23.setStockStatus(StockStatus.IN_STOCK);
        item23.setPurchaseDate(LocalDate.of(2023, 5, 8));
        item23.setExpiryDate(LocalDate.of(2023, 6, 8));
        item23.setDaysLeft(item23.getExpiryDate().toEpochDay() - item23.getPurchaseDate().toEpochDay());
        item23.setDescription("Fresh garlic cloves");

        Item item24 = new Item();
        item24.setName("Lemon");
        item24.setCategory("Fruits");
        item24.setStock(15);
        item24.setCalories(17.0);
        item24.setProtein(0.6);
        item24.setFat(0.2);
        item24.setStockStatus(StockStatus.IN_STOCK);
        item24.setPurchaseDate(LocalDate.of(2023, 5, 10));
        item24.setExpiryDate(LocalDate.of(2023, 5, 20));
        item24.setDaysLeft(item24.getExpiryDate().toEpochDay() - item24.getPurchaseDate().toEpochDay());
        item24.setDescription("Fresh lemons");

        Item item25 = new Item();
        item25.setName("Spices");
        item25.setCategory("Seasonings");
        item25.setStock(20);
        item25.setCalories(10.0);
        item25.setProtein(0.2);
        item25.setFat(0.1);
        item25.setStockStatus(StockStatus.IN_STOCK);
        item25.setPurchaseDate(LocalDate.of(2023, 3, 1));
        item25.setExpiryDate(LocalDate.of(2024, 3, 1));
        item25.setDaysLeft(item25.getExpiryDate().toEpochDay() - item25.getPurchaseDate().toEpochDay());
        item25.setDescription("Assorted spices and herbs");

        Item item26 = new Item();
        item26.setName("Chips");
        item26.setCategory("Snacks");
        item26.setStock(40);
        item26.setCalories(160.0);
        item26.setProtein(2.0);
        item26.setFat(10.0);
        item26.setStockStatus(StockStatus.IN_STOCK);
        item26.setPurchaseDate(LocalDate.of(2023, 5, 5));
        item26.setExpiryDate(LocalDate.of(2023, 7, 5));
        item26.setDaysLeft(item26.getExpiryDate().toEpochDay() - item26.getPurchaseDate().toEpochDay());
        item26.setDescription("Potato chips");

        Item item27 = new Item();
        item27.setName("Beer");
        item27.setCategory("Beverages");
        item27.setStock(24);
        item27.setCalories(150.0);
        item27.setProtein(1.6);
        item27.setFat(0.0);
        item27.setStockStatus(StockStatus.IN_STOCK);
        item27.setPurchaseDate(LocalDate.of(2023, 5, 1));
        item27.setExpiryDate(LocalDate.of(2024, 5, 1));
        item27.setDaysLeft(item27.getExpiryDate().toEpochDay() - item27.getPurchaseDate().toEpochDay());
        item27.setDescription("Craft beer");

        Item item28 = new Item();
        item28.setName("Wine");
        item28.setCategory("Beverages");
        item28.setStock(12);
        item28.setCalories(120.0);
        item28.setProtein(0.1);
        item28.setFat(0.0);
        item28.setStockStatus(StockStatus.IN_STOCK);
        item28.setPurchaseDate(LocalDate.of(2023, 4, 15));
        item28.setExpiryDate(LocalDate.of(2025, 4, 15));
        item28.setDaysLeft(item28.getExpiryDate().toEpochDay() - item28.getPurchaseDate().toEpochDay());
        item28.setDescription("Red wine");

        Item item29 = new Item();
        item29.setName("Soda");
        item29.setCategory("Beverages");
        item29.setStock(30);
        item29.setCalories(140.0);
        item29.setProtein(0.0);
        item29.setFat(0.0);
        item29.setStockStatus(StockStatus.IN_STOCK);
        item29.setPurchaseDate(LocalDate.of(2023, 5, 10));
        item29.setExpiryDate(LocalDate.of(2023, 11, 10));
        item29.setDaysLeft(item29.getExpiryDate().toEpochDay() - item29.getPurchaseDate().toEpochDay());
        item29.setDescription("Cola soda");
        Item item30 = new Item();
        item30.setName("Bacon");
        item30.setCategory("Meats");
        item30.setStock(15);
        item30.setCalories(540.0);
        item30.setProtein(37.0);
        item30.setFat(42.0);
        item30.setStockStatus(StockStatus.IN_STOCK);
        item30.setPurchaseDate(LocalDate.of(2023, 5, 12));
        item30.setExpiryDate(LocalDate.of(2023, 6, 12));
        item30.setDaysLeft(item30.getExpiryDate().toEpochDay() - item30.getPurchaseDate().toEpochDay());
        item30.setDescription("Smoked bacon");
        Item item31 = new Item();
        item31.setName("Prawn");
        item31.setCategory("Seafood");
        item31.setStock(20);
        item31.setCalories(100.0);
        item31.setProtein(24.0);
        item31.setFat(1.0);
        item31.setStockStatus(StockStatus.IN_STOCK);
        item31.setPurchaseDate(LocalDate.of(2023, 5, 15));
        item31.setExpiryDate(LocalDate.of(2023, 5, 20));
        item31.setDaysLeft(item31.getExpiryDate().toEpochDay() - item31.getPurchaseDate().toEpochDay());
        item31.setDescription("Fresh prawns");

        Item item32 = new Item();
        item32.setName("Pork");
        item32.setCategory("Meats");
        item32.setStock(10);
        item32.setCalories(250.0);
        item32.setProtein(27.0);
        item32.setFat(14.0);
        item32.setStockStatus(StockStatus.IN_STOCK);
        item32.setPurchaseDate(LocalDate.of(2023, 5, 13));
        item32.setExpiryDate(LocalDate.of(2023, 5, 23));
        item32.setDaysLeft(item32.getExpiryDate().toEpochDay() - item32.getPurchaseDate().toEpochDay());
        item32.setDescription("Pork chops");

        Item item33 = new Item();
        item33.setName("Lamb");
        item33.setCategory("Meats");
        item33.setStock(8);
        item33.setCalories(280.0);
        item33.setProtein(25.0);
        item33.setFat(20.0);
        item33.setStockStatus(StockStatus.IN_STOCK);
        item33.setPurchaseDate(LocalDate.of(2023, 5, 14));
        item33.setExpiryDate(LocalDate.of(2023, 5, 24));
        item33.setDaysLeft(item33.getExpiryDate().toEpochDay() - item33.getPurchaseDate().toEpochDay());
        item33.setDescription("Lamb leg");

        FridgeInventory fridgeInventory1 = new FridgeInventory();
        fridgeInventory1.setProfile(profile);
        fridgeInventory1.addItem(item1);
        fridgeInventory1.addItem(item2);
        fridgeInventory1.addItem(item3);
        fridgeInventory1.addItem(item4);
        fridgeInventory1.addItem(item5);
        fridgeInventory1.addItem(item6);
        fridgeInventory1.addItem(item19);
        fridgeInventory1.addItem(item20);
        fridgeInventory1.addItem(item21);
        fridgeInventory1.addItem(item22);
        fridgeInventory1.addItem(item23);
        fridgeInventory1.addItem(item24);
        fridgeInventory1.addItem(item25);
        fridgeInventory1.addItem(item26);

        FridgeInventory fridgeInventory2 = new FridgeInventory();
        fridgeInventory2.setProfile(profile2);
        fridgeInventory2.addItem(item7);
        fridgeInventory2.addItem(item8);
        fridgeInventory2.addItem(item9);
        fridgeInventory2.addItem(item10);
        fridgeInventory2.addItem(item11);
        fridgeInventory2.addItem(item12);
        fridgeInventory2.addItem(item13);
        fridgeInventory2.addItem(item14);
        fridgeInventory2.addItem(item27);
        fridgeInventory2.addItem(item28);
        fridgeInventory2.addItem(item29);
        fridgeInventory2.addItem(item30);
        fridgeInventory2.addItem(item31);
        fridgeInventory2.addItem(item32);

        FridgeInventory fridgeInventory3 = new FridgeInventory();
        fridgeInventory3.setProfile(profile3);
        fridgeInventory3.addItem(item15);
        fridgeInventory3.addItem(item16);
        fridgeInventory3.addItem(item17);
        fridgeInventory3.addItem(item18);
        fridgeInventory3.addItem(item33);

        SharedFridge sharedFridge = new SharedFridge();
        sharedFridge.setId(1L);
        sharedFridge.setUser(user);
        sharedFridge.addFridgeInventory(fridgeInventory1);
        sharedFridge.addFridgeInventory(fridgeInventory2);
        sharedFridge.addFridgeInventory(fridgeInventory3);
//
//
        userService.saveInit(user);
        sharedFridgeInventory.save(sharedFridge);

        // Initialize ADMIN (NO TOUCH!)
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@admin.com");
        admin.setPassword("admin");
        admin.setType(RoleList.ADMIN);
        userService.saveInit(admin);


        Meal meal1 = new Meal();
        meal1.setName("Chicken Salad");
        // make meal1 set image that will pass into the path /Users/evanle/Smart-Fridge-Inventory-Server/Image in my spring boot project
        meal1.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/Chicken.jpeg");
        meal1.setCookingTime("30 minutes");
        ArrayList<String> ingredients1 = new ArrayList<>();
        ingredients1.add("chicken");
        ingredients1.add("Lettuce");
        ingredients1.add("Tomato");
        ingredients1.add("Cucumber");
        ingredients1.add("Onion");
        ingredients1.add("Olive oil");
        meal1.setIngredients(ingredients1);
        ArrayList<DietaryList> diaryList = new ArrayList<>();
        diaryList.add(DietaryList.GLUTEN_FREE);
        diaryList.add(DietaryList.DAIRY_FREE);
        meal1.setDietaryLists(diaryList);
        meal1.setCookingMethod("1. Cook chicken\n2. Mix all ingredients\n3. Serve with olive oil");

        // Meal 2
        Meal meal2 = new Meal();
        meal2.setName("Chicken Soup");
        meal2.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/ChickenSoup.jpeg");
        meal2.setCookingTime("45 minutes");
        ArrayList<String> ingredients2 = new ArrayList<>();
        ingredients2.add("chicken");
        ingredients2.add("carrots");
        ingredients2.add("celery");
        ingredients2.add("onion");
        ingredients2.add("garlic");
        meal2.setIngredients(ingredients2);
        ArrayList<DietaryList> dietaryList2 = new ArrayList<>();
        dietaryList2.add(DietaryList.GLUTEN_FREE);
        meal2.setDietaryLists(dietaryList2);
        meal2.setCookingMethod("1. Cook chicken\n2. Add vegetables\n3. Simmer until flavors combine");

        // Meal 3
        Meal meal3 = new Meal();
        meal3.setName("Chicken Pasta");
        meal3.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/ChickenPasta.jpeg");
        meal3.setCookingTime("30 minutes");
        ArrayList<String> ingredients3 = new ArrayList<>();
        ingredients3.add("chicken");
        ingredients3.add("pasta");
        ingredients3.add("tomato sauce");
        ingredients3.add("garlic");
        ingredients3.add("basil");
        meal3.setIngredients(ingredients3);
        ArrayList<DietaryList> dietaryList3 = new ArrayList<>();
        dietaryList3.add(DietaryList.DAIRY_FREE);
        meal3.setDietaryLists(dietaryList3);
        meal3.setCookingMethod("1. Cook chicken\n2. Cook pasta\n3. Combine with sauce and herbs");

        // Meal 4
        Meal meal4 = new Meal();
        meal4.setName("Chicken Stir Fry");
        meal4.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/ChickenStirFry.jpeg");
        meal4.setCookingTime("25 minutes");
        ArrayList<String> ingredients4 = new ArrayList<>();
        ingredients4.add("chicken");
        ingredients4.add("bell peppers");
        ingredients4.add("onion");
        ingredients4.add("soy sauce");
        ingredients4.add("ginger");
        meal4.setIngredients(ingredients4);
        ArrayList<DietaryList> dietaryList4 = new ArrayList<>();
        dietaryList4.add(DietaryList.GLUTEN_FREE);
        meal4.setDietaryLists(dietaryList4);
        meal4.setCookingMethod("1. Cook chicken\n2. Stir fry vegetables\n3. Combine with sauce");

        // Meal 5
        Meal meal5 = new Meal();
        meal5.setName("Green Curry");
        meal5.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/ChickenCurry.jpeg");
        meal5.setCookingTime("40 minutes");
        ArrayList<String> ingredients5 = new ArrayList<>();
        ingredients5.add("chicken");
        ingredients5.add("green curry powder");
        ingredients5.add("coconut milk");
        ingredients5.add("onion");
        ingredients5.add("garlic");
        ingredients5.add("bell peppers");
        meal5.setIngredients(ingredients5);
        ArrayList<DietaryList> dietaryList5 = new ArrayList<>();
        dietaryList5.add(DietaryList.GLUTEN_FREE);
        meal5.setDietaryLists(dietaryList5);
        meal5.setCookingMethod("1. Cook chicken\n2. Add spices and coconut milk\n3. Simmer until flavors combine");

        // Meal 6
        Meal meal6 = new Meal();
        meal6.setName("Beef Tacos");
        meal6.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefTacos.jpeg");
        meal6.setCookingTime("30 minutes");
        ArrayList<String> ingredients6 = new ArrayList<>();
        ingredients6.add("beef");
        ingredients6.add("taco shells");
        ingredients6.add("lettuce");
        ingredients6.add("tomato");
        ingredients6.add("cheese");
        ingredients6.add("sour cream");
        meal6.setIngredients(ingredients6);
        ArrayList<DietaryList> dietaryList6 = new ArrayList<>();
        dietaryList6.add(DietaryList.GLUTEN_FREE);
        meal6.setDietaryLists(dietaryList6);
        meal6.setCookingMethod("1. Cook ground beef\n2. Add taco seasoning\n3. Serve in taco shells with toppings");

        // Meal 7
        Meal meal7 = new Meal();
        meal7.setName("Beef Stroganoff");
        meal7.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefStroganoff.jpeg");
        meal7.setCookingTime("45 minutes");
        ArrayList<String> ingredients7 = new ArrayList<>();
        ingredients7.add("beef");
        ingredients7.add("mushrooms");
        ingredients7.add("onion");
        ingredients7.add("sour cream");
        ingredients7.add("beef broth");
        ingredients7.add("egg noodles");
        meal7.setIngredients(ingredients7);
        ArrayList<DietaryList> dietaryList7 = new ArrayList<>();
        dietaryList7.add(DietaryList.SOY_FREE);
        meal7.setDietaryLists(dietaryList7);
        meal7.setCookingMethod("1. Cook beef and onions\n2. Add mushrooms and broth\n3. Stir in sour cream\n4. Serve over egg noodles");

        // Meal 8
        Meal meal8 = new Meal();
        meal8.setName("Beef Fajitas");
        meal8.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefFajitas.jpeg");
        meal8.setCookingTime("25 minutes");
        ArrayList<String> ingredients8 = new ArrayList<>();
        ingredients8.add("beef");
        ingredients8.add("bell peppers");
        ingredients8.add("onion");
        ingredients8.add("fajita seasoning");
        ingredients8.add("tortillas");
        ingredients8.add("guacamole");
        meal8.setIngredients(ingredients8);
        ArrayList<DietaryList> dietaryList8 = new ArrayList<>();
        dietaryList8.add(DietaryList.GLUTEN_FREE);
        meal8.setDietaryLists(dietaryList8);
        meal8.setCookingMethod("1. Cook beef and vegetables\n2. Add fajita seasoning\n3. Serve in tortillas with guacamole");

        // Meal 9
        Meal meal9 = new Meal();
        meal9.setName("Beef Burgers");
        meal9.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefBurgers.jpeg");
        meal9.setCookingTime("20 minutes");
        ArrayList<String> ingredients9 = new ArrayList<>();
        ingredients9.add("beef");
        ingredients9.add("burger buns");
        ingredients9.add("lettuce");
        ingredients9.add("tomato");
        ingredients9.add("onion");
        ingredients9.add("cheese");
        meal9.setIngredients(ingredients9);
        ArrayList<DietaryList> dietaryList9 = new ArrayList<>();
        dietaryList9.add(DietaryList.GLUTEN_FREE);
        meal9.setDietaryLists(dietaryList9);
        meal9.setCookingMethod("1. Form beef into patties\n2. Grill or pan-fry\n3. Serve on buns with toppings");

        // Meal 10
        Meal meal10 = new Meal();
        meal10.setName("Beef Stir Fry");
        meal10.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefStirFry.jpeg");
        meal10.setCookingTime("30 minutes");
        ArrayList<String> ingredients10 = new ArrayList<>();
        ingredients10.add("beef");
        ingredients10.add("broccoli");
        ingredients10.add("carrots");
        ingredients10.add("bell peppers");
        ingredients10.add("soy sauce");
        ingredients10.add("rice");
        meal10.setIngredients(ingredients10);
        ArrayList<DietaryList> dietaryList10 = new ArrayList<>();
        dietaryList10.add(DietaryList.GLUTEN_FREE);
        meal10.setDietaryLists(dietaryList10);
        meal10.setCookingMethod("1. Cook beef and vegetables\n2. Add soy sauce\n3. Serve over rice");

        // Meal 11
        Meal meal11 = new Meal();
        meal11.setName("Beef Chili");
        meal11.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefChili.jpeg");
        meal11.setCookingTime("60 minutes");
        ArrayList<String> ingredients11 = new ArrayList<>();
        ingredients11.add("beef");
        ingredients11.add("kidney beans");
        ingredients11.add("tomato sauce");
        ingredients11.add("chili powder");
        ingredients11.add("onion");
        ingredients11.add("garlic");
        meal11.setIngredients(ingredients11);
        ArrayList<DietaryList> dietaryList11 = new ArrayList<>();
        dietaryList11.add(DietaryList.GLUTEN_FREE);
        dietaryList11.add(DietaryList.DAIRY_FREE);
        dietaryList11.add(DietaryList.NUT_FREE);
        meal11.setDietaryLists(dietaryList11);
        meal11.setCookingMethod("1. Cook beef and onions\n2. Add beans and spices\n3. Simmer until flavors combine");

        // Meal 12
        Meal meal12 = new Meal();
        meal12.setName("Beef Meatballs");
        meal12.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefMeatballs.jpeg");
        meal12.setCookingTime("45 minutes");
        ArrayList<String> ingredients12 = new ArrayList<>();
        ingredients12.add("beef");
        ingredients12.add("breadcrumbs");
        ingredients12.add("eggs");
        ingredients12.add("parmesan cheese");
        ingredients12.add("garlic");
        ingredients12.add("marinara sauce");
        meal12.setIngredients(ingredients12);
        ArrayList<DietaryList> dietaryList12 = new ArrayList<>();
        dietaryList12.add(DietaryList.DAIRY_FREE);
        meal12.setDietaryLists(dietaryList12);
        meal12.setCookingMethod("1. Form beef into meatballs\n2. Bake or fry\n3. Serve with marinara sauce");

        // Meal 13
        Meal meal13 = new Meal();
        meal13.setName("Beef Enchiladas");
        meal13.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefEnchiladas.jpeg");
        meal13.setCookingTime("60 minutes");
        ArrayList<String> ingredients13 = new ArrayList<>();
        ingredients13.add("beef");
        ingredients13.add("enchilada sauce");
        ingredients13.add("tortillas");
        ingredients13.add("cheese");
        ingredients13.add("onion");
        ingredients13.add("bell peppers");
        meal13.setIngredients(ingredients13);
        ArrayList<DietaryList> dietaryList13 = new ArrayList<>();
        dietaryList13.add(DietaryList.GLUTEN_FREE);
        meal13.setDietaryLists(dietaryList13);
        meal13.setCookingMethod("1. Cook beef and vegetables\n2. Roll in tortillas with sauce and cheese\n3. Bake until heated through");

        // Meal 14
        Meal meal14 = new Meal();
        meal14.setName("Beef Lasagna");
        meal14.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefLasagna.jpeg");
        meal14.setCookingTime("90 minutes");
        ArrayList<String> ingredients14 = new ArrayList<>();
        ingredients14.add("beef");
        ingredients14.add("lasagna noodles");
        ingredients14.add("marinara sauce");
        ingredients14.add("ricotta cheese");
        ingredients14.add("mozzarella cheese");
        ingredients14.add("parmesan cheese");
        meal14.setIngredients(ingredients14);
        ArrayList<DietaryList> dietaryList14 = new ArrayList<>();
        dietaryList14.add(DietaryList.DAIRY_FREE);
        dietaryList14.add(DietaryList.BEEF_PRODUCTS);
        dietaryList14.add(DietaryList.BAKED_PRODUCTS);
        dietaryList14.add(DietaryList.SOUPS);
        meal14.setDietaryLists(dietaryList14);
        meal14.setCookingMethod("1. Cook beef and marinara sauce\n2. Layer with noodles and cheeses\n3. Bake until heated through");

        // Meal 15
        Meal meal15 = new Meal();
        meal15.setName("Beef Pot Roast");
        meal15.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefPotRoast.jpeg");
        meal15.setCookingTime("180 minutes");
        ArrayList<String> ingredients15 = new ArrayList<>();
        ingredients15.add("beef");
        ingredients15.add("carrots");
        ingredients15.add("potatoes");
        ingredients15.add("onion");
        ingredients15.add("beef broth");
        ingredients15.add("garlic");
        meal15.setIngredients(ingredients15);
        ArrayList<DietaryList> dietaryList15 = new ArrayList<>();
        dietaryList15.add(DietaryList.GLUTEN_FREE);
        dietaryList15.add(DietaryList.DAIRY_FREE);
        meal15.setDietaryLists(dietaryList15);
        meal15.setCookingMethod("1. Sear beef roast\n2. Add vegetables and broth\n3. Slow cook until beef is tender");

        // Meal 16
        Meal meal16 = new Meal();
        meal16.setName("Beef Bolognese");
        meal16.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefBolognese.jpeg");
        meal16.setCookingTime("60 minutes");
        ArrayList<String> ingredients16 = new ArrayList<>();
        ingredients16.add("beef");
        ingredients16.add("tomato sauce");
        ingredients16.add("onion");
        ingredients16.add("garlic");
        ingredients16.add("carrots");
        ingredients16.add("pasta");
        meal16.setIngredients(ingredients16);
        ArrayList<DietaryList> dietaryList16 = new ArrayList<>();
        dietaryList16.add(DietaryList.NO_SUGAR_ADDED);
        dietaryList16.add(DietaryList.DAIRY_FREE);
        meal16.setDietaryLists(dietaryList16);
        meal16.setCookingMethod("1. Cook beef and vegetables\n2. Add tomato sauce\n3. Simmer until flavors combine\n4. Serve with pasta");

        // Meal 17
        Meal meal17 = new Meal();
        meal17.setName("Beef Stuffed Peppers");
        meal17.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefStuffedPeppers.jpeg");
        meal17.setCookingTime("45 minutes");
        ArrayList<String> ingredients17 = new ArrayList<>();
        ingredients17.add("beef");
        ingredients17.add("bell peppers");
        ingredients17.add("rice");
        ingredients17.add("tomato sauce");
        ingredients17.add("cheese");
        meal17.setIngredients(ingredients17);
        ArrayList<DietaryList> dietaryList17 = new ArrayList<>();
        dietaryList17.add(DietaryList.GLUTEN_FREE);
        meal17.setDietaryLists(dietaryList17);
        meal17.setCookingMethod("1. Cook beef and rice\n2. Stuff bell peppers with beef mixture\n3. Top with tomato sauce and cheese\n4. Bake until heated through");

        // Meal 18
        Meal meal18 = new Meal();
        meal18.setName("Beef Quesadillas");
        meal18.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefQuesadillas.jpeg");
        meal18.setCookingTime("20 minutes");
        ArrayList<String> ingredients18 = new ArrayList<>();
        ingredients18.add("beef");
        ingredients18.add("tortillas");
        ingredients18.add("cheese");
        ingredients18.add("salsa");
        ingredients18.add("guacamole");
        meal18.setIngredients(ingredients18);
        ArrayList<DietaryList> dietaryList18 = new ArrayList<>();
        dietaryList18.add(DietaryList.GLUTEN_FREE);
        meal18.setDietaryLists(dietaryList18);
        meal18.setCookingMethod("1. Cook beef\n2. Layer beef and cheese between tortillas\n3. Grill or pan-fry\n4. Serve with salsa and guacamole");

        // Meal 19
        Meal meal19 = new Meal();
        meal19.setName("Beef Kabobs");
        meal19.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefKabobs.jpeg");
        meal19.setCookingTime("30 minutes");
        ArrayList<String> ingredients19 = new ArrayList<>();
        ingredients19.add("beef");
        ingredients19.add("bell peppers");
        ingredients19.add("onion");
        ingredients19.add("mushrooms");
        ingredients19.add("zucchini");
        meal19.setIngredients(ingredients19);
        ArrayList<DietaryList> dietaryList19 = new ArrayList<>();
        dietaryList19.add(DietaryList.NO_SUGAR_ADDED);
        dietaryList19.add(DietaryList.DAIRY_FREE);
        meal19.setDietaryLists(dietaryList19);
        meal19.setCookingMethod("1. Skewer beef and vegetables\n2. Grill or broil\n3. Serve with rice or salad");

        // Meal 20
        Meal meal20 = new Meal();
        meal20.setName("Beef Stew");
        meal20.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefStew.jpeg");
        meal20.setCookingTime("90 minutes");
        ArrayList<String> ingredients20 = new ArrayList<>();
        ingredients20.add("beef");
        ingredients20.add("potatoes");
        ingredients20.add("carrots");
        ingredients20.add("onion");
        ingredients20.add("beef broth");
        ingredients20.add("garlic");
        meal20.setIngredients(ingredients20);
        ArrayList<DietaryList> dietaryList20 = new ArrayList<>();
        dietaryList20.add(DietaryList.GLUTEN_FREE);
        meal20.setDietaryLists(dietaryList20);
        meal20.setCookingMethod("1. Brown beef\n2. Add vegetables and broth\n3. Simmer until beef is tender");

        // Meal 21
        Meal meal21 = new Meal();
        meal21.setName("Beef Wraps");
        meal21.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefWraps.jpeg");
        meal21.setCookingTime("25 minutes");
        ArrayList<String> ingredients21 = new ArrayList<>();
        ingredients21.add("beef");
        ingredients21.add("tortillas");
        ingredients21.add("lettuce");
        ingredients21.add("tomato");
        ingredients21.add("cheese");
        ingredients21.add("ranch dressing");
        meal21.setIngredients(ingredients21);
        ArrayList<DietaryList> dietaryList21 = new ArrayList<>();
        dietaryList21.add(DietaryList.GLUTEN_FREE);
        meal21.setDietaryLists(dietaryList21);
        meal21.setCookingMethod("1. Cook beef\n2. Warm tortillas\n3. Layer beef and toppings in tortillas");

        // Meal 22
        Meal meal22 = new Meal();
        meal22.setName("Beef Empanadas");
        meal22.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefEmpanadas.jpeg");
        meal22.setCookingTime("60 minutes");
        ArrayList<String> ingredients22 = new ArrayList<>();
        ingredients22.add("beef");
        ingredients22.add("onion");
        ingredients22.add("garlic");
        ingredients22.add("empanada dough");
        ingredients22.add("egg");
        meal22.setIngredients(ingredients22);
        ArrayList<DietaryList> dietaryList22 = new ArrayList<>();
        dietaryList22.add(DietaryList.EGG_FREE);
        meal22.setDietaryLists(dietaryList22);
        meal22.setCookingMethod("1. Cook beef and vegetables\n2. Fill empanada dough with beef mixture\n3. Seal and bake until golden brown");

        // Meal 23
        Meal meal23 = new Meal();
        meal23.setName("Beef Sloppy Joes");
        meal23.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefSloppyJoes.jpeg");
        meal23.setCookingTime("30 minutes");
        ArrayList<String> ingredients23 = new ArrayList<>();
        ingredients23.add("beef");
        ingredients23.add("tomato sauce");
        ingredients23.add("onion");
        ingredients23.add("garlic");
        ingredients23.add("burger buns");
        meal23.setIngredients(ingredients23);
        ArrayList<DietaryList> dietaryList23 = new ArrayList<>();
        dietaryList23.add(DietaryList.GLUTEN_FREE);
        dietaryList23.add(DietaryList.DAIRY_FREE);
        dietaryList23.add(DietaryList.NUT_FREE);
        meal23.setDietaryLists(dietaryList23);
        meal23.setCookingMethod("1. Cook beef and onions\n2. Add tomato sauce and spices\n3. Simmer until thickened\n4. Serve on burger buns");

        // Meal 24
        Meal meal24 = new Meal();
        meal24.setName("Beef Stroganoff Casserole");
        meal24.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefStroganoffCasserole.jpeg");
        meal24.setCookingTime("75 minutes");
        ArrayList<String> ingredients24 = new ArrayList<>();
        ingredients24.add("beef");
        ingredients24.add("mushrooms");
        ingredients24.add("onion");
        ingredients24.add("sour cream");
        ingredients24.add("egg noodles");
        meal24.setIngredients(ingredients24);
        ArrayList<DietaryList> dietaryList24 = new ArrayList<>();

        meal24.setDietaryLists(dietaryList24);
        meal24.setCookingMethod("1. Cook beef and onions\n2. Add mushrooms and sour cream\n3. Layer with egg noodles in a casserole dish\n4. Bake until heated through");

        // Meal 25
        Meal meal25 = new Meal();
        meal25.setName("Beef Philly Cheesesteaks");
        meal25.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefPhillyCheesesteaks.jpeg");
        meal25.setCookingTime("40 minutes");
        ArrayList<String> ingredients25 = new ArrayList<>();
        ingredients25.add("beef");
        ingredients25.add("bell peppers");
        ingredients25.add("onion");
        ingredients25.add("provolone cheese");
        ingredients25.add("hoagie rolls");
        meal25.setIngredients(ingredients25);
        ArrayList<DietaryList> dietaryList25 = new ArrayList<>();
        dietaryList25.add(DietaryList.GLUTEN_FREE);
        meal25.setDietaryLists(dietaryList25);
        meal25.setCookingMethod("1. Cook beef and vegetables\n2. Toast hoagie rolls\n3. Layer beef, vegetables, and cheese on rolls");

        // Meal 26
        Meal meal26 = new Meal();
        meal26.setName("Beef Stuffed Mushrooms");
        meal26.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BeefStuffedMushrooms.jpeg");
        meal26.setCookingTime("45 minutes");
        ArrayList<String> ingredients26 = new ArrayList<>();
        ingredients26.add("beef");
        ingredients26.add("mushrooms");
        ingredients26.add("breadcrumbs");
        ingredients26.add("parmesan cheese");
        ingredients26.add("garlic");
        meal26.setIngredients(ingredients26);
        ArrayList<DietaryList> dietaryList26 = new ArrayList<>();
        dietaryList26.add(DietaryList.GLUTEN_FREE);
        meal26.setDietaryLists(dietaryList26);
        meal26.setCookingMethod("1. Cook beef and mix with breadcrumbs, cheese, and garlic\n2. Stuff mushroom caps with beef mixture\n3. Bake until heated through");

        Meal meal27 = new Meal();
        meal27.setName("Pork Stir-Fry");
        meal27.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PorkStirFry.jpeg");
        meal27.setCookingTime("30 minutes");
        ArrayList<String> ingredients27 = new ArrayList<>();
        ingredients27.add("pork");
        ingredients27.add("bell peppers");
        ingredients27.add("soy sauce");
        ingredients27.add("ginger");
        ingredients27.add("garlic");
        ingredients27.add("broccoli");
        meal27.setIngredients(ingredients27);
        ArrayList<DietaryList> dietaryList27 = new ArrayList<>();
        dietaryList27.add(DietaryList.DAIRY_FREE);
        meal27.setDietaryLists(dietaryList27);
        meal27.setCookingMethod("1. Slice pork and vegetables\n2. Stir-fry pork until browned\n3. Add vegetables, soy sauce, ginger, and garlic\n4. Cook until vegetables are tender");

        Meal meal28 = new Meal();
        meal28.setName("Pork Tacos");
        meal28.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PorkTacos.jpeg");
        meal28.setCookingTime("25 minutes");
        ArrayList<String> ingredients28 = new ArrayList<>();
        ingredients28.add("pork");
        ingredients28.add("tortillas");
        ingredients28.add("onion");
        ingredients28.add("cilantro");
        ingredients28.add("lime");
        ingredients28.add("spices");
        meal28.setIngredients(ingredients28);
        ArrayList<DietaryList> dietaryList28 = new ArrayList<>();
        dietaryList28.add(DietaryList.GLUTEN_FREE);
        meal28.setDietaryLists(dietaryList28);
        meal28.setCookingMethod("1. Cook pork with spices\n2. Warm tortillas\n3. Assemble tacos with pork, onion, cilantro, and lime juice");

        Meal meal29 = new Meal();
        meal29.setName("Pork Chops with Apples");
        meal29.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PorkChopsWithApples.jpeg");
        meal29.setCookingTime("40 minutes");
        ArrayList<String> ingredients29 = new ArrayList<>();
        ingredients29.add("pork chops");
        ingredients29.add("apples");
        ingredients29.add("onions");
        ingredients29.add("butter");
        ingredients29.add("cinnamon");
        meal29.setIngredients(ingredients29);
        ArrayList<DietaryList> dietaryList29 = new ArrayList<>();
        dietaryList29.add(DietaryList.GLUTEN_FREE);
        meal29.setDietaryLists(dietaryList29);
        meal29.setCookingMethod("1. Sear pork chops until browned\n2. Cook apples and onions in butter with cinnamon\n3. Add pork chops to apple mixture and cook until done");

        Meal meal30 = new Meal();
        meal30.setName("BBQ Pulled Pork Sandwiches");
        meal30.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BBQPulledPorkSandwiches.jpeg");
        meal30.setCookingTime("6 hours");
        ArrayList<String> ingredients30 = new ArrayList<>();
        ingredients30.add("pork shoulder");
        ingredients30.add("BBQ sauce");
        ingredients30.add("bread rolls");
        ingredients30.add("coleslaw");
        meal30.setIngredients(ingredients30);
        ArrayList<DietaryList> dietaryList30 = new ArrayList<>();
        dietaryList30.add(DietaryList.DAIRY_FREE);
        meal30.setDietaryLists(dietaryList30);
        meal30.setCookingMethod("1. Slow cook pork shoulder with BBQ sauce\n2. Shred pork\n3. Serve on rolls with coleslaw");

        Meal meal31 = new Meal();
        meal31.setName("Pork and Pineapple Skewers");
        meal31.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PorkAndPineappleSkewers.jpeg");
        meal31.setCookingTime("30 minutes");
        ArrayList<String> ingredients31 = new ArrayList<>();
        ingredients31.add("pork");
        ingredients31.add("pineapple");
        ingredients31.add("bell peppers");
        ingredients31.add("teriyaki sauce");
        meal31.setIngredients(ingredients31);
        ArrayList<DietaryList> dietaryList31 = new ArrayList<>();
        dietaryList31.add(DietaryList.GLUTEN_FREE);
        meal31.setDietaryLists(dietaryList31);
        meal31.setCookingMethod("1. Cut pork, pineapple, and bell peppers into chunks\n2. Thread onto skewers\n3. Brush with teriyaki sauce\n4. Grill until pork is cooked through");

        Meal meal32 = new Meal();
        meal32.setName("Grilled Salmon with Asparagus");
        meal32.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/GrilledSalmonWithAsparagus.jpeg");
        meal32.setCookingTime("20 minutes");
        ArrayList<String> ingredients32 = new ArrayList<>();
        ingredients32.add("salmon fillets");
        ingredients32.add("asparagus");
        ingredients32.add("olive oil");
        ingredients32.add("lemon");
        ingredients32.add("garlic");
        meal32.setIngredients(ingredients32);
        ArrayList<DietaryList> dietaryList32 = new ArrayList<>();
        dietaryList32.add(DietaryList.GLUTEN_FREE);
        dietaryList32.add(DietaryList.DAIRY_FREE);
        meal32.setDietaryLists(dietaryList32);
        meal32.setCookingMethod("1. Preheat grill\n2. Season salmon and asparagus with olive oil, lemon, and garlic\n3. Grill salmon and asparagus until cooked through");

        Meal meal33 = new Meal();
        meal33.setName("Baked Salmon with Dill Sauce");
        meal33.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/BakedSalmonWithDillSauce.jpeg");
        meal33.setCookingTime("30 minutes");
        ArrayList<String> ingredients33 = new ArrayList<>();
        ingredients33.add("salmon fillets");
        ingredients33.add("dill");
        ingredients33.add("yogurt");
        ingredients33.add("lemon");
        ingredients33.add("garlic");
        meal33.setIngredients(ingredients33);
        ArrayList<DietaryList> dietaryList33 = new ArrayList<>();
        dietaryList33.add(DietaryList.GLUTEN_FREE);
        meal33.setDietaryLists(dietaryList33);
        meal33.setCookingMethod("1. Preheat oven to 375°F\n2. Season salmon with lemon and garlic\n3. Bake salmon for 20 minutes\n4. Mix dill, yogurt, and lemon juice for sauce\n5. Serve salmon with dill sauce");

        Meal meal34 = new Meal();
        meal34.setName("Salmon Sushi Rolls");
        meal34.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/SalmonSushiRolls.jpeg");
        meal34.setCookingTime("50 minutes");
        ArrayList<String> ingredients34 = new ArrayList<>();
        ingredients34.add("salmon");
        ingredients34.add("sushi rice");
        ingredients34.add("nori sheets");
        ingredients34.add("cucumber");
        ingredients34.add("avocado");
        meal34.setIngredients(ingredients34);
        ArrayList<DietaryList> dietaryList34 = new ArrayList<>();
        dietaryList34.add(DietaryList.GLUTEN_FREE);
        meal34.setDietaryLists(dietaryList34);
        meal34.setCookingMethod("1. Cook sushi rice and let cool\n2. Slice salmon, cucumber, and avocado\n3. Place nori sheet on bamboo mat, add rice and ingredients\n4. Roll tightly and slice into pieces");

        Meal meal35 = new Meal();
        meal35.setName("Teriyaki Salmon Bowls");
        meal35.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/TeriyakiSalmonBowls.jpeg");
        meal35.setCookingTime("30 minutes");
        ArrayList<String> ingredients35 = new ArrayList<>();
        ingredients35.add("salmon fillets");
        ingredients35.add("teriyaki sauce");
        ingredients35.add("rice");
        ingredients35.add("broccoli");
        ingredients35.add("carrots");
        meal35.setIngredients(ingredients35);
        ArrayList<DietaryList> dietaryList35 = new ArrayList<>();
        dietaryList35.add(DietaryList.DAIRY_FREE);
        meal35.setDietaryLists(dietaryList35);
        meal35.setCookingMethod("1. Cook rice according to package instructions\n2. Steam broccoli and carrots\n3. Cook salmon with teriyaki sauce until done\n4. Serve salmon over rice with vegetables");

        Meal meal36 = new Meal();
        meal36.setName("Salmon and Quinoa Salad");
        meal36.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/SalmonAndQuinoaSalad.jpeg");
        meal36.setCookingTime("25 minutes");
        ArrayList<String> ingredients36 = new ArrayList<>();
        ingredients36.add("salmon fillets");
        ingredients36.add("quinoa");
        ingredients36.add("spinach");
        ingredients36.add("cherry tomatoes");
        ingredients36.add("feta cheese");
        ingredients36.add("lemon");
        meal36.setIngredients(ingredients36);
        ArrayList<DietaryList> dietaryList36 = new ArrayList<>();
        dietaryList36.add(DietaryList.GLUTEN_FREE);
        meal36.setDietaryLists(dietaryList36);
        meal36.setCookingMethod("1. Cook quinoa according to package instructions\n2. Cook salmon until done\n3. Toss quinoa with spinach, cherry tomatoes, and feta cheese\n4. Top with cooked salmon and a squeeze of lemon");

        Meal meal37 = new Meal();
        meal37.setName("Lamb Chops with Rosemary");
        meal37.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/LambChopsWithRosemary.jpeg");
        meal37.setCookingTime("25 minutes");
        ArrayList<String> ingredients37 = new ArrayList<>();
        ingredients37.add("lamb chops");
        ingredients37.add("rosemary");
        ingredients37.add("garlic");
        ingredients37.add("olive oil");
        ingredients37.add("lemon");
        meal37.setIngredients(ingredients37);
        ArrayList<DietaryList> dietaryList37 = new ArrayList<>();
        dietaryList37.add(DietaryList.GLUTEN_FREE);
        meal37.setDietaryLists(dietaryList37);
        meal37.setCookingMethod("1. Season lamb chops with rosemary, garlic, olive oil, and lemon\n2. Grill or pan-sear lamb chops until cooked to desired doneness");

        Meal meal38 = new Meal();
        meal38.setName("Lamb Curry");
        meal38.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/LambCurry.jpeg");
        meal38.setCookingTime("1 hour");
        ArrayList<String> ingredients38 = new ArrayList<>();
        ingredients38.add("lamb");
        ingredients38.add("onion");
        ingredients38.add("garlic");
        ingredients38.add("ginger");
        ingredients38.add("tomatoes");
        ingredients38.add("spices");
        meal38.setIngredients(ingredients38);
        ArrayList<DietaryList> dietaryList38 = new ArrayList<>();
        dietaryList38.add(DietaryList.GLUTEN_FREE);
        dietaryList38.add(DietaryList.DAIRY_FREE);
        meal38.setDietaryLists(dietaryList38);
        meal38.setCookingMethod("1. Sauté onions, garlic, and ginger\n2. Add lamb and brown\n3. Add tomatoes and spices\n4. Simmer until lamb is tender");

        Meal meal39 = new Meal();
        meal39.setName("Lamb Kebabs");
        meal39.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/LambKebabs.jpeg");
        meal39.setCookingTime("30 minutes");
        ArrayList<String> ingredients39 = new ArrayList<>();
        ingredients39.add("lamb");
        ingredients39.add("bell peppers");
        ingredients39.add("onions");
        ingredients39.add("yogurt");
        ingredients39.add("spices");
        meal39.setIngredients(ingredients39);
        ArrayList<DietaryList> dietaryList39 = new ArrayList<>();
        dietaryList39.add(DietaryList.GLUTEN_FREE);
        meal39.setDietaryLists(dietaryList39);
        meal39.setCookingMethod("1. Marinate lamb in yogurt and spices\n2. Thread lamb, bell peppers, and onions onto skewers\n3. Grill until lamb is cooked through");

        Meal meal40 = new Meal();
        meal40.setName("Lamb Stew");
        meal40.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/LambStew.jpeg");
        meal40.setCookingTime("2 hours");
        ArrayList<String> ingredients40 = new ArrayList<>();
        ingredients40.add("lamb");
        ingredients40.add("carrots");
        ingredients40.add("potatoes");
        ingredients40.add("onions");
        ingredients40.add("celery");
        ingredients40.add("beef broth");
        meal40.setIngredients(ingredients40);
        ArrayList<DietaryList> dietaryList40 = new ArrayList<>();
        dietaryList40.add(DietaryList.GLUTEN_FREE);
        meal40.setDietaryLists(dietaryList40);
        meal40.setCookingMethod("1. Brown lamb in a pot\n2. Add onions, carrots, and celery, and cook until softened\n3. Add potatoes and beef broth\n4. Simmer until lamb is tender");

        Meal meal41 = new Meal();
        meal41.setName("Garlic Butter Prawns");
        meal41.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/GarlicButterPrawns.jpeg");
        meal41.setCookingTime("15 minutes");
        ArrayList<String> ingredients41 = new ArrayList<>();
        ingredients41.add("prawns");
        ingredients41.add("garlic");
        ingredients41.add("butter");
        ingredients41.add("lemon");
        ingredients41.add("parsley");
        meal41.setIngredients(ingredients41);
        ArrayList<DietaryList> dietaryList41 = new ArrayList<>();
        dietaryList41.add(DietaryList.GLUTEN_FREE);
        meal41.setDietaryLists(dietaryList41);
        meal41.setCookingMethod("1. Melt butter in a pan\n2. Add garlic and cook until fragrant\n3. Add prawns and cook until pink\n4. Squeeze lemon juice over prawns and sprinkle with parsley");

        Meal meal42 = new Meal();
        meal42.setName("Prawn Stir-Fry");
        meal42.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PrawnStirFry.jpeg");
        meal42.setCookingTime("20 minutes");
        ArrayList<String> ingredients42 = new ArrayList<>();
        ingredients42.add("prawns");
        ingredients42.add("bell peppers");
        ingredients42.add("broccoli");
        ingredients42.add("soy sauce");
        ingredients42.add("ginger");
        ingredients42.add("garlic");
        meal42.setIngredients(ingredients42);
        ArrayList<DietaryList> dietaryList42 = new ArrayList<>();
        dietaryList42.add(DietaryList.DAIRY_FREE);
        meal42.setDietaryLists(dietaryList42);
        meal42.setCookingMethod("1. Stir-fry prawns until pink\n2. Add garlic and ginger, cook until fragrant\n3. Add vegetables and soy sauce\n4. Cook until vegetables are tender");

        Meal meal43 = new Meal();
        meal43.setName("Prawn Tacos");
        meal43.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PrawnTacos.jpeg");
        meal43.setCookingTime("20 minutes");
        ArrayList<String> ingredients43 = new ArrayList<>();
        ingredients43.add("prawns");
        ingredients43.add("tortillas");
        ingredients43.add("cabbage");
        ingredients43.add("lime");
        ingredients43.add("avocado");
        ingredients43.add("spices");
        meal43.setIngredients(ingredients43);
        ArrayList<DietaryList> dietaryList43 = new ArrayList<>();
        dietaryList43.add(DietaryList.DAIRY_FREE);
        meal43.setDietaryLists(dietaryList43);
        meal43.setCookingMethod("1. Season prawns with spices and cook until pink\n2. Warm tortillas\n3. Assemble tacos with prawns, cabbage, avocado, and a squeeze of lime");

        Meal meal44 = new Meal();
        meal44.setName("Prawn and Mango Salad");
        meal44.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PrawnAndMangoSalad.jpeg");
        meal44.setCookingTime("15 minutes");
        ArrayList<String> ingredients44 = new ArrayList<>();
        ingredients44.add("prawns");
        ingredients44.add("mango");
        ingredients44.add("mixed greens");
        ingredients44.add("red onion");
        ingredients44.add("lime");
        ingredients44.add("cilantro");
        meal44.setIngredients(ingredients44);
        ArrayList<DietaryList> dietaryList44 = new ArrayList<>();
        dietaryList44.add(DietaryList.GLUTEN_FREE);
        meal44.setDietaryLists(dietaryList44);
        meal44.setCookingMethod("1. Cook prawns until pink\n2. Toss mixed greens, mango, and red onion\n3. Add prawns on top\n4. Dress with lime juice and cilantro");

        Meal meal45 = new Meal();
        meal45.setName("Prawn Risotto");
        meal45.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PrawnRisotto.jpeg");
        meal45.setCookingTime("40 minutes");
        ArrayList<String> ingredients45 = new ArrayList<>();
        ingredients45.add("prawns");
        ingredients45.add("arborio rice");
        ingredients45.add("chicken broth");
        ingredients45.add("onion");
        ingredients45.add("parmesan cheese");
        ingredients45.add("white wine");
        meal45.setIngredients(ingredients45);
        ArrayList<DietaryList> dietaryList45 = new ArrayList<>();
        dietaryList45.add(DietaryList.GLUTEN_FREE);
        meal45.setDietaryLists(dietaryList45);
        meal45.setCookingMethod("1. Cook onions in butter until soft\n2. Add arborio rice and cook for 2 minutes\n3. Add white wine and cook until absorbed\n4. Gradually add broth, stirring until absorbed\n5. Stir in prawns and cook until pink\n6. Finish with parmesan cheese");

        Meal meal46 = new Meal();
        meal46.setName("Prawn Pad Thai");
        meal46.setMealImageUrl("/Users/evanle/Smart-Fridge-Inventory-Server/Image/PrawnPadThai.jpeg");
        meal46.setCookingTime("30 minutes");
        ArrayList<String> ingredients46 = new ArrayList<>();
        ingredients46.add("prawns");
        ingredients46.add("rice noodles");
        ingredients46.add("eggs");
        ingredients46.add("bean sprouts");
        ingredients46.add("peanuts");
        ingredients46.add("pad thai sauce");
        meal46.setIngredients(ingredients46);
        ArrayList<DietaryList> dietaryList46 = new ArrayList<>();
        dietaryList46.add(DietaryList.GLUTEN_FREE);
        meal46.setDietaryLists(dietaryList46);
        meal46.setCookingMethod("1. Cook rice noodles according to package instructions\n2. Stir-fry prawns until pink\n3. Push prawns to the side and scramble eggs in the same pan\n4. Add cooked noodles and pad thai sauce\n5. Toss with bean sprouts and peanuts");

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName("Basic Shopping List");
        ArrayList<String> items = new ArrayList<>();
        items.add("Chicken");
        items.add("Beef");
        items.add("Pork");
        items.add("Lettuce");
        items.add("Tomato");
        items.add("Cucumber");
        items.add("Onion");
        items.add("Olive oil");
        items.add("Orange Juice");
        items.add("Yogurt");
        items.add("Peanut Butter");
        items.add("Salmon");
        items.add("Spinach");
        items.add("Greek Yogurt");
        items.add("Duck");
        items.add("Almond Milk");
        items.add("Broccoli");
        shoppingList.setItems(items);
        shoppingList.setCost(170.3);
        shoppingList.setEstimatedDate(7);
        shoppingList.setDescription("Basic shopping list for the week");

        // Shopping List 2
        ShoppingList shoppingList2 = new ShoppingList();
        shoppingList2.setName("Vegan Shopping List");
        ArrayList<String> items2 = new ArrayList<>();
        items2.add("Lentils");
        items2.add("Quinoa");
        items2.add("Kale");
        items2.add("Avocado");
        items2.add("Bananas");
        items2.add("Chickpeas");
        items2.add("Coconut Milk");
        items2.add("Tofu");
        items2.add("Cashews");
        items2.add("Chia Seeds");
        items2.add("Hummus");
        items2.add("Mushrooms");
        items2.add("Nutritional Yeast");
        items2.add("Soy Sauce");
        shoppingList2.setItems(items2);
        shoppingList2.setCost(120.5);
        shoppingList2.setEstimatedDate(7);
        shoppingList2.setDescription("Vegan shopping list for the week");

        // Shopping List 3
        ShoppingList shoppingList3 = new ShoppingList();
        shoppingList3.setName("Family Dinner Shopping List");
        ArrayList<String> items3 = new ArrayList<>();
        items3.add("Chicken");
        items3.add("Pasta");
        items3.add("Tomato");
        items3.add("Garlic Bread");
        items3.add("Salad Mix");
        items3.add("Dressing");
        items3.add("Ground Beef");
        items3.add("Cheese");
        items3.add("Bread");
        items3.add("Milk");
        items3.add("Eggs");
        items3.add("Butter");
        items3.add("Frozen Vegetables");
        items3.add("Ice Cream");
        shoppingList3.setItems(items3);
        shoppingList3.setCost(90.75);
        shoppingList3.setEstimatedDate(7);
        shoppingList3.setDescription("Shopping list for family dinner this week");

        // Shopping List 4
        ShoppingList shoppingList4 = new ShoppingList();
        shoppingList4.setName("Healthy Breakfast Shopping List");
        ArrayList<String> items4 = new ArrayList<>();
        items4.add("Oats");
        items4.add("Eggs");
        items4.add("Yogurt");
        items4.add("Berries");
        items4.add("Bananas");
        items4.add("Almond Butter");
        items4.add("Whole Grain Bread");
        items4.add("Spinach");
        items4.add("Almond Milk");
        items4.add("Chia Seeds");
        items4.add("Flaxseed");
        items4.add("Honey");
        items4.add("Cottage Cheese");
        items4.add("Oranges");
        shoppingList4.setItems(items4);
        shoppingList4.setCost(85.2);
        shoppingList4.setEstimatedDate(7);
        shoppingList4.setDescription("Shopping list for healthy breakfast options this week");

        ShoppingList shoppingList5 = new ShoppingList();
        shoppingList5.setName("Weekly Dinner Shopping List");
        ArrayList<String> items5 = new ArrayList<>();
        items5.add("Chicken");
        items5.add("Broccoli");
        items5.add("Sweet Potatoes");
        items5.add("Brown Rice");
        items5.add("Garlic");
        items5.add("Olive Oil");
        items5.add("Bell Peppers");
        items5.add("Quinoa");
        items5.add("Tomato Sauce");
        items5.add("Ground Turkey");
        items5.add("Zucchini");
        items5.add("Parmesan Cheese");
        items5.add("Salmon Fillets");
        items5.add("Asparagus");
        shoppingList5.setItems(items5);
        shoppingList5.setCost(120.75);
        shoppingList5.setEstimatedDate(7);
        shoppingList5.setDescription("Shopping list for a variety of healthy dinner options this week");

        ShoppingList shoppingList6 = new ShoppingList();
        shoppingList6.setName("Vegan Meal Prep Shopping List");
        ArrayList<String> items6 = new ArrayList<>();
        items6.add("Chickpeas");
        items6.add("Black Beans");
        items6.add("Tofu");
        items6.add("Kale");
        items6.add("Avocados");
        items6.add("Brown Rice");
        items6.add("Quinoa");
        items6.add("Tomatoes");
        items6.add("Bell Peppers");
        items6.add("Red Onion");
        items6.add("Lemon");
        items6.add("Nutritional Yeast");
        items6.add("Tahini");
        items6.add("Spinach");
        shoppingList6.setItems(items6);
        shoppingList6.setCost(95.30);
        shoppingList6.setEstimatedDate(7);
        shoppingList6.setDescription("Shopping list for vegan meal prep for the week");

        // ShoppingList shoppingList7
        ShoppingList shoppingList7 = new ShoppingList();
        shoppingList7.setName("Healthy Snack Shopping List");
        ArrayList<String> items7 = new ArrayList<>();
        items7.add("Apples");
        items7.add("Bananas");
        items7.add("Oranges");
        items7.add("Grapes");
        items7.add("Carrots");
        items7.add("Celery");
        items7.add("Hummus");
        items7.add("Whole Grain Crackers");
        items7.add("Almonds");
        items7.add("Walnuts");
        items7.add("Greek Yogurt");
        items7.add("Granola");
        items7.add("Dark Chocolate");
        shoppingList7.setItems(items7);
        shoppingList7.setCost(45.75);
        shoppingList7.setEstimatedDate(3);
        shoppingList7.setDescription("Shopping list for healthy snacks for the week");

        // ShoppingList shoppingList8
        ShoppingList shoppingList8 = new ShoppingList();
        shoppingList8.setName("Outdoor Picnic Shopping List");
        ArrayList<String> items8 = new ArrayList<>();
        items8.add("Sandwich Bread");
        items8.add("Deli Meats");
        items8.add("Cheese");
        items8.add("Lettuce");
        items8.add("Tomatoes");
        items8.add("Potato Chips");
        items8.add("Watermelon");
        items8.add("Lemonade");
        items8.add("Paper Plates");
        items8.add("Napkins");
        items8.add("Plastic Utensils");
        shoppingList8.setItems(items8);
        shoppingList8.setCost(32.50);
        shoppingList8.setEstimatedDate(3);
        shoppingList8.setDescription("Shopping list for an outdoor picnic");

        // ShoppingList shoppingList9
        ShoppingList shoppingList9 = new ShoppingList();
        shoppingList9.setName("Mexican Fiesta Shopping List");
        ArrayList<String> items9 = new ArrayList<>();
        items9.add("Tortilla Chips");
        items9.add("Salsa");
        items9.add("Guacamole");
        items9.add("Refried Beans");
        items9.add("Rice");
        items9.add("Chicken");
        items9.add("Taco Seasoning");
        items9.add("Shredded Cheese");
        items9.add("Sour Cream");
        items9.add("Limes");
        items9.add("Avocados");
        items9.add("Cilantro");
        shoppingList9.setItems(items9);
        shoppingList9.setCost(58.20);
        shoppingList9.setEstimatedDate(3);
        shoppingList9.setDescription("Shopping list for a Mexican fiesta party");

        // ShoppingList shoppingList10
        ShoppingList shoppingList10 = new ShoppingList();
        shoppingList10.setName("Breakfast Essentials Shopping List");
        ArrayList<String> items10 = new ArrayList<>();
        items10.add("Eggs");
        items10.add("Bacon");
        items10.add("Bread");
        items10.add("Butter");
        items10.add("Milk");
        items10.add("Orange Juice");
        items10.add("Coffee");
        items10.add("Oatmeal");
        items10.add("Berries");
        items10.add("Maple Syrup");
        shoppingList10.setItems(items10);
        shoppingList10.setCost(38.75);
        shoppingList10.setEstimatedDate(3);
        shoppingList10.setDescription("Shopping list for breakfast essentials");

        // ShoppingList shoppingList11
        ShoppingList shoppingList11 = new ShoppingList();
        shoppingList11.setName("Game Night Snacks Shopping List");
        ArrayList<String> items11 = new ArrayList<>();
        items11.add("Popcorn");
        items11.add("Pretzels");
        items11.add("Peanuts");
        items11.add("Chips");
        items11.add("Salsa");
        items11.add("Guacamole");
        items11.add("Soda");
        items11.add("Beer");
        items11.add("Wine");
        shoppingList11.setItems(items11);
        shoppingList11.setCost(42.20);
        shoppingList11.setEstimatedDate(3);
        shoppingList11.setDescription("Shopping list for game night snacks and drinks");

        // ShoppingList shoppingList12
        ShoppingList shoppingList12 = new ShoppingList();
        shoppingList12.setName("Baking Supplies Shopping List");
        ArrayList<String> items12 = new ArrayList<>();
        items12.add("All-Purpose Flour");
        items12.add("Sugar");
        items12.add("Baking Soda");
        items12.add("Baking Powder");
        items12.add("Vanilla Extract");
        items12.add("Eggs");
        items12.add("Butter");
        items12.add("Milk");
        items12.add("Chocolate Chips");
        items12.add("Sprinkles");
        shoppingList12.setItems(items12);
        shoppingList12.setCost(25.50);
        shoppingList12.setEstimatedDate(3);
        shoppingList12.setDescription("Shopping list for baking supplies");

        // ShoppingList shoppingList13
        ShoppingList shoppingList13 = new ShoppingList();
        shoppingList13.setName("Summer BBQ Shopping List");
        ArrayList<String> items13 = new ArrayList<>();
        items13.add("Hamburger Patties");
        items13.add("Hot Dogs");
        items13.add("Buns");
        items13.add("Lettuce");
        items13.add("Tomatoes");
        items13.add("Onions");
        items13.add("Ketchup");
        items13.add("Mustard");
        items13.add("Potato Chips");
        items13.add("Soda");
        items13.add("Charcoal");
        shoppingList13.setItems(items13);
        shoppingList13.setCost(65.75);
        shoppingList13.setEstimatedDate(3);
        shoppingList13.setDescription("Shopping list for a summer BBQ party");

        // ShoppingList shoppingList14
        ShoppingList shoppingList14 = new ShoppingList();
        shoppingList14.setName("Italian Dinner Shopping List");
        ArrayList<String> items14 = new ArrayList<>();
        items14.add("Spaghetti");
        items14.add("Tomato Sauce");
        items14.add("Ground Beef");
        items14.add("Garlic Bread");
        items14.add("Parmesan Cheese");
        items14.add("Salad Mix");
        items14.add("Tomatoes");
        items14.add("Cucumbers");
        items14.add("Italian Dressing");
        items14.add("Red Wine");
        shoppingList14.setItems(items14);
        shoppingList14.setCost(42.80);
        shoppingList14.setEstimatedDate(3);
        shoppingList14.setDescription("Shopping list for an Italian dinner");

        // ShoppingList shoppingList15
        ShoppingList shoppingList15 = new ShoppingList();
        shoppingList15.setName("Smoothie Ingredients Shopping List");
        ArrayList<String> items15 = new ArrayList<>();
        items15.add("Bananas");
        items15.add("Strawberries");
        items15.add("Blueberries");
        items15.add("Spinach");
        items15.add("Kale");
        items15.add("Greek Yogurt");
        items15.add("Almond Milk");
        items15.add("Honey");
        items15.add("Chia Seeds");
        items15.add("Protein Powder");
        shoppingList15.setItems(items15);
        shoppingList15.setCost(29.95);
        shoppingList15.setEstimatedDate(3);
        shoppingList15.setDescription("Shopping list for smoothie ingredients");

        // ShoppingList shoppingList16
        ShoppingList shoppingList16 = new ShoppingList();
        shoppingList16.setName("Meal Prep for the Week Shopping List");
        ArrayList<String> items16 = new ArrayList<>();
        items16.add("Chicken Breasts");
        items16.add("Salmon Fillets");
        items16.add("Broccoli");
        items16.add("Sweet Potatoes");
        items16.add("Brown Rice");
        items16.add("Quinoa");
        items16.add("Olive Oil");
        items16.add("Garlic");
        items16.add("Onions");
        items16.add("Lemon");
        items16.add("Spices");
        shoppingList16.setItems(items16);
        shoppingList16.setCost(78.40);
        shoppingList16.setEstimatedDate(7);
        shoppingList16.setDescription("Shopping list for meal prepping for the week");

        // ShoppingList shoppingList17
        ShoppingList shoppingList17 = new ShoppingList();
        shoppingList17.setName("Kids' Lunch Box Shopping List");
        ArrayList<String> items17 = new ArrayList<>();
        items17.add("Peanut Butter");
        items17.add("Jelly");
        items17.add("Bread");
        items17.add("Apples");
        items17.add("Carrots");
        items17.add("Granola Bars");
        items17.add("Juice Boxes");
        items17.add("Sandwich Bags");
        items17.add("Snack Bags");
        shoppingList17.setItems(items17);
        shoppingList17.setCost(32.65);
        shoppingList17.setEstimatedDate(7);
        shoppingList17.setDescription("Shopping list for kids' lunch boxes for the week");

        // ShoppingList shoppingList18
        ShoppingList shoppingList18 = new ShoppingList();
        shoppingList18.setName("Camping Trip Shopping List");
        ArrayList<String> items18 = new ArrayList<>();
        items18.add("Tent");
        items18.add("Sleeping Bags");
        items18.add("Camping Stove");
        items18.add("Cooking Utensils");
        items18.add("Canned Food");
        items18.add("Firewood");
        items18.add("Matches");
        items18.add("Flashlights");
        items18.add("Bug Spray");
        items18.add("First Aid Kit");
        shoppingList18.setItems(items18);
        shoppingList18.setCost(225.95);
        shoppingList18.setEstimatedDate(7);
        shoppingList18.setDescription("Shopping list for a camping trip");


        /*
         * List of meals
         * 6 chicken meals
         * 20 beef meals
         * */
        mealRepository.save(meal1);
        mealRepository.save(meal2);
        mealRepository.save(meal3);
        mealRepository.save(meal4);
        mealRepository.save(meal5);
        mealRepository.save(meal6);
        mealRepository.save(meal7);
        mealRepository.save(meal8);
        mealRepository.save(meal9);
        mealRepository.save(meal10);
        mealRepository.save(meal11);
        mealRepository.save(meal12);
        mealRepository.save(meal13);
        mealRepository.save(meal14);
        mealRepository.save(meal15);
        mealRepository.save(meal16);
        mealRepository.save(meal17);
        mealRepository.save(meal18);
        mealRepository.save(meal19);
        mealRepository.save(meal20);
        mealRepository.save(meal21);
        mealRepository.save(meal22);
        mealRepository.save(meal23);
        mealRepository.save(meal24);
        mealRepository.save(meal25);
        mealRepository.save(meal26);
        mealRepository.save(meal27);
        mealRepository.save(meal28);
        mealRepository.save(meal29);
        mealRepository.save(meal30);
        mealRepository.save(meal31);
        mealRepository.save(meal32);
        mealRepository.save(meal33);
        mealRepository.save(meal34);
        mealRepository.save(meal35);
        mealRepository.save(meal36);
        mealRepository.save(meal37);
        mealRepository.save(meal38);
        mealRepository.save(meal39);
        mealRepository.save(meal40);
        mealRepository.save(meal41);
        mealRepository.save(meal42);
        mealRepository.save(meal43);
        mealRepository.save(meal44);
        mealRepository.save(meal45);
        mealRepository.save(meal46);

        shoppingListRepository.save(shoppingList);
        shoppingListRepository.save(shoppingList2);
        shoppingListRepository.save(shoppingList3);
        shoppingListRepository.save(shoppingList4);
        shoppingListRepository.save(shoppingList5);
        shoppingListRepository.save(shoppingList6);
        shoppingListRepository.save(shoppingList7);
        shoppingListRepository.save(shoppingList8);
        shoppingListRepository.save(shoppingList9);
        shoppingListRepository.save(shoppingList10);
        shoppingListRepository.save(shoppingList11);
        shoppingListRepository.save(shoppingList12);
        shoppingListRepository.save(shoppingList13);
        shoppingListRepository.save(shoppingList14);
        shoppingListRepository.save(shoppingList15);
        shoppingListRepository.save(shoppingList16);
        shoppingListRepository.save(shoppingList17);
        shoppingListRepository.save(shoppingList18);




    }
}