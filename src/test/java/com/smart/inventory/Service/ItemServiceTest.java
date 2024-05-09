package com.smart.inventory.Service;

import com.smart.inventory.Entity.Item;
import com.smart.inventory.Repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService = new ItemService(itemRepository);
    }

    @Test
    void deleteItem() {
        // Given
        Item item = new Item();
        item.setId(1L);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        // When
        itemService.deleteItem(1L);

        // Then
        Mockito.verify(itemRepository, Mockito.times(1)).delete(item);
    }
}