package com.smart.inventory.Service;

import com.smart.inventory.Entity.ShoppingList;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Repository.ShoppingListRepository;
import com.smart.inventory.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final UserRepository userRepository;


    public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
    }

    public List<ShoppingList> findAll() {
        return this.shoppingListRepository.findAll();
    }

    public List<ShoppingList> generateShoppingList(List<String> items, int expectedDays) {
        List<ShoppingList> allShoppingLists = this.shoppingListRepository.findAll();

        List<String> lowerCaseItems = items.stream()
                .map(String::toLowerCase)
                .toList();

        List<ShoppingList> suitableShoppingLists = allShoppingLists.stream()
                // Filter items whether if lower case or upper case
                .filter(shop -> shop.getItems().stream()
                        .map(String::toLowerCase)
                        .anyMatch(lowerCaseItems::contains))
                .filter(shop -> shop.getEstimatedDate() == expectedDays)
                .collect(Collectors.toList());

        if (suitableShoppingLists.isEmpty()) {
            throw new RuntimeException("No suitable shopping lists found");
        }

        return suitableShoppingLists;
    }

    public void AssignShoppingListToUser(Long userId, Long shoppingListId) {
        ShoppingList shoppingListToBeAssigned = this.shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found!"));

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (shoppingListToBeAssigned.getUser().contains(user)) {
                throw new RuntimeException("Shopping List already assigned to the user!");
            }

        shoppingListToBeAssigned.getUser().add(user);
        this.shoppingListRepository.save(shoppingListToBeAssigned);
    }

    public List<ShoppingList> getShoppingListByUser(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return user.getShoppingLists();
    }

    @Transactional
    public void deleteShoppingListFromUser(Long userId, Long shoppingListId) {
        ShoppingList shoppingListToBeDeleted = this.shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new RuntimeException("Shopping List not found!"));

        User userToBeDeleted = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (shoppingListToBeDeleted.getUser() == null) {
            throw new RuntimeException("Shopping List not assigned to a user!");
        }
        shoppingListToBeDeleted.getUser().remove(userToBeDeleted);
        userToBeDeleted.removeShoppingList(shoppingListToBeDeleted);
        this.userRepository.save(userToBeDeleted);
    }

}
