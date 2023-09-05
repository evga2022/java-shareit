package ru.practicum.shareit.item;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> getAll(int userId);

    Item getById(Integer id);

    Item save(Item item);

    Item update(Item item);
    List<Item> searchItem(String text);
}
