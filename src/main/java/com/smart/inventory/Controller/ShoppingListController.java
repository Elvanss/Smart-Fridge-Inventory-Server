package com.smart.inventory.Controller;

import com.smart.inventory.DTO.ShoppingListDTO;
import com.smart.inventory.Entity.ShoppingList;
import com.smart.inventory.Entity.User;
import com.smart.inventory.Mapper.ShoppingListMapper;
import com.smart.inventory.Service.ShoppingListService;
import com.smart.inventory.System.Result;
import com.smart.inventory.System.StatusCode;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/shopping-lists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final ShoppingListMapper shoppingListMapper;

    public ShoppingListController(ShoppingListService shoppingListService, ShoppingListMapper shoppingListMapper) {
        this.shoppingListService = shoppingListService;
        this.shoppingListMapper = shoppingListMapper;
    }

    @GetMapping
    public Result getShoppingLists() {
        List<ShoppingList> shoppingLists = this.shoppingListService.findAll();
        List<ShoppingListDTO> shoppingListDto = shoppingLists.stream()
                .map(this.shoppingListMapper::toDTO)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "All Shopping Lists", shoppingListDto);
    }

    @GetMapping("/generate")
    public Result generateShoppingList(@RequestParam("items") List<String> items,
                                       @RequestParam("days") int expectedDays) {
        List<ShoppingList> allShoppingLists = this.shoppingListService.generateShoppingList(items, expectedDays);
        List<ShoppingListDTO> shoppingListDTOS = allShoppingLists.stream()
                .map(this.shoppingListMapper::toDTO)
                .toList();

        return new Result(true, StatusCode.SUCCESS, "Suitable Shopping Lists", shoppingListDTOS);
    }

    @PostMapping("/assign")
    public Result AddShoppingListToFavorite(@RequestParam("userId") Long userId,
                                            @RequestParam("shoppingListId") Long shoppingListId) {
        this.shoppingListService.AssignShoppingListToUser(userId, shoppingListId);
        return new Result(true, StatusCode.SUCCESS, "Shopping List Added to Favorite", null);
    }

    @DeleteMapping("/delete/{userId}/{shoppingListId}")
    public Result deleteShoppingListFromFavorite(@PathVariable Long userId, @PathVariable Long shoppingListId) {
        this.shoppingListService.deleteShoppingListFromUser(userId, shoppingListId);
        return new Result(true, StatusCode.SUCCESS, "Shopping List Deleted from Favorite", null);
    }

    @GetMapping("/view/{userId}")
    public Result viewShoppingListByUser(@PathVariable Long userId) {
        List<ShoppingList> shoppingLists = this.shoppingListService.getShoppingListByUser(userId);
        List<ShoppingListDTO> shoppingListDTOS = shoppingLists.stream()
                .map(this.shoppingListMapper::toDTO)
                .toList();
        return new Result(true, StatusCode.SUCCESS, "Shopping List by User", shoppingListDTOS);
    }

}


