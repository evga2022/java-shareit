package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems(int userId);

    Item getItemById(Integer id);

    Item saveItem(Item item, int userId);

    Item updateItem(Integer itemId, int userId, Item item);

    List<Item> searchItem(String text);
}
