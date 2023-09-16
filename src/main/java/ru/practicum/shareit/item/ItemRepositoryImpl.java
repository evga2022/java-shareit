package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private List<Item> items = new ArrayList<>();
    private Integer nextId = 1;

    private Integer getNewId() {
        return nextId++;
    }

    @Override
    public List<Item> getAll(int userId) {
        return items.stream()
                .filter(item -> item.getOwnerId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Item getById(Integer itemId) {
        return items.stream().filter(item -> item.getItemId() == itemId).findFirst().orElse(null);
    }

    @Override
    public Item save(Item item) {
        item.setItemId(getNewId());
        items.add(item);
        return item;
    }

    @Override
    public Item update(Item item) {
        delete(item.getItemId());
        items.add(item);
        return item;
    }

    @Override
    public List<Item> searchItem(String text) {
        return items.stream()
                .filter(item -> (item.getItemName().toUpperCase().contains(text.toUpperCase())
                        || item.getDescription().toUpperCase().contains(text.toUpperCase())) && item.getAvailable())
                .collect(Collectors.toList());
    }

    private void delete(Integer id) {
        items = items.stream().filter(item -> item.getItemId() != id).collect(Collectors.toList());
    }
}
