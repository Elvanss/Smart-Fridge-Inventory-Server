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
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final MealRepository mealRepository;

    public DBInitializer(UserRepository userRepository,
                         ProfileRepository profileRepository,
                         FridgeInventoryRepository fridgeInventoryRepository,
                         UserService userService, ItemRepository itemRepository,
                         MealRepository mealRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.fridgeInventoryRepository = fridgeInventoryRepository;
        this.userService = userService;
        this.itemRepository = itemRepository;
        this.mealRepository = mealRepository;
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
        item1.setDescription("Fresh Milk");
//        Item itemSaved = itemRepository.save(item1);

        Item item2 = new Item();
        item2.setName("Bread");
        item2.setCategory("Bakery");
        item2.setStock(2);
        item2.setCalories(250.0);
        item2.setStockStatus(StockStatus.IN_STOCK);
        item2.setPurchaseDate(LocalDate.of(2021, 10, 2));
        item2.setExpiryDate(LocalDate.of(2021, 10, 7));
        item2.setDescription("Whole grain bread");

        Item item3 = new Item();
        item3.setName("Apple");
        item3.setCategory("Fruit");
        item3.setStock(10);
        item3.setCalories(52.0);
        item3.setStockStatus(StockStatus.IN_STOCK);
        item3.setPurchaseDate(LocalDate.of(2021, 10, 3));
        item3.setExpiryDate(LocalDate.of(2021, 10, 13));
        item3.setDescription("Red apples");

        Item item4 = new Item();
        item4.setName("Eggs");
        item4.setCategory("Dairy");
        item4.setStock(12);
        item4.setCalories(68.0);
        item4.setStockStatus(StockStatus.IN_STOCK);
        item4.setPurchaseDate(LocalDate.of(2021, 10, 4));
        item4.setExpiryDate(LocalDate.of(2021, 10, 18));
        item4.setDescription("Free-range eggs");

        Item item5 = new Item();
        item5.setName("Cheese");
        item5.setCategory("Dairy");
        item5.setStock(1);
        item5.setCalories(402.0);
        item5.setStockStatus(StockStatus.IN_STOCK);
        item5.setPurchaseDate(LocalDate.of(2021, 10, 5));
        item5.setExpiryDate(LocalDate.of(2021, 10, 20));
        item5.setDescription("Cheddar cheese");

        Item item6 = new Item();
        item6.setName("Chicken");
        item6.setCategory("Meat");
        item6.setStock(1);
        item6.setCalories(335.0);
        item6.setStockStatus(StockStatus.IN_STOCK);
        item6.setPurchaseDate(LocalDate.of(2021, 10, 6));
        item6.setExpiryDate(LocalDate.of(2021, 10, 9));
        item6.setDescription("Chicken breast");



        // Create Fridges Inventory for User
        FridgeInventory fridgeInventory2 = new FridgeInventory();
        fridgeInventory2.setLocation("Kitchen");
        fridgeInventory2.setCapacity(100);
        fridgeInventory2.setUser(user2);
        fridgeInventory2.addItem(item1);
        fridgeInventory2.addItem(item2);
        fridgeInventory2.addItem(item3);
        fridgeInventory2.addItem(item4);
        fridgeInventory2.addItem(item5);
        fridgeInventory2.addItem(item6);


        // Make a full detail meal

//        public enum DietaryList {
//            VEGAN,
//            VEGETARIAN,
//            PESCATARIAN,
//            KETO,
//            PALEO,
//            MEDITERRANEAN,
//            DASH,
//            WHOLE30,
//            FLEXITARIAN,
//            LOW_FODMAP,
//            GLUTEN_FREE,
//            DAIRY_FREE,
//            NUT_FREE,
//            SOY_FREE,
//            EGG_FREE,
//            SHELLFISH_FREE,
//            WHEAT_FREE,
//            FISH_FREE,
//            RED_MEAT_FREE,
//            PORK_FREE,
//            CRUSTACEAN_FREE,
//            CELERY_FREE,
//            MUSTARD_FREE,
//            SESAME_FREE,
//            LUPINE_FREE,
//            MOLLUSK_FREE,
//            ALCOHOL_FREE,
//            NO_OIL_ADDED,
//            NO_SUGAR_ADDED,
//            NO_SUGAR,
//            NO_OIL,
//            LOW_SUGAR,
//            LOW_FAT,
//            LOW_CALORIE,
//            HIGH_PROTEIN,
//            HIGH_FIBER,
//            LOW_CARB,
//            LOW_SODIUM,
//            LOW_CHOLESTEROL,
//            SUGAR_CONSCIOUS,
//            FAT_FREE,
//            KIDNEY_FRIENDLY,
//            KOSHER,
//            HALAL,
//            ALCOHOL_CONSCIOUS,
//            IMMUNE_SUPPORTIVE,
//            GUT_FRIENDLY,
//            WHEAT_FREE_GLUTEN_FREE,
//            SUGAR_FREE,
//            LOW_POTASSIUM,
//            VEGETABLE,
//            FRUIT,
//            DAIRY,
//            GRAIN,
//            PROTEIN,
//            SEAFOOD,
//            FATS_AND_OILS,
//            SWEETENERS,
//            SPICES_AND_SEASONINGS,
//            BEVERAGES,
//            LEGUMES,
//            NUTS_AND_SEEDS,
//            BAKED_PRODUCTS,
//            SWEETS,
//            CEREALS,
//            FAST_FOOD,
//            MEALS,
//            SNACKS,
//            RESTAURANT_FOODS,
//            DAIRY_AND_EGG_PRODUCTS,
//            SAUSAGES_AND_LUNCHEON_MEATS,
//            BREAKFAST_CEREALS,
//            SOUPS,
//            BEEF_PRODUCTS,
//            FINFISH_AND_SHELLFISH_PRODUCTS,
//            LEGUMES_AND_LEGUME_PRODUCTS,
//            LAMB_VEAL_AND_GAME_PRODUCTS,
//            PORK_PRODUCTS,
//            POULTRY_PRODUCTS,
//            SWE
//        }

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


        // Save to DB
        fridgeInventoryRepository.save(fridgeInventory2);

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



        // Print to console
        log.info("Users: {}", userRepository.findAll());
        log.info("Profiles: {}", profileRepository.findAll());
    }
}
