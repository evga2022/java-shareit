package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.item.model.Item;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems(int userId) {
        return itemRepository.getAll(userId);
    }

    @Override
    public Item getItemById(Integer id) {
        return itemRepository.getById(id);
    }

    @Override
    public Item saveItem(Item item, int userId) {
        if (item.getAvailable() == null
                || (item.getItemName() == null || item.getItemName().isEmpty())
                || (item.getDescription() == null || item.getDescription().isEmpty())) {
            throw new ValidationException();
        }
        item.setOwnerId(userId);
        Item savedItem = itemRepository.save(item);
        log.info("Добавлен Item(id = {}, name = {}) пользователем {}", item.getItemId(), item.getItemName(), userId);
        return savedItem;
    }

    @Override
    public Item updateItem(Integer itemId, int userId, Item item) {
        Item itemToUpdate = getItemById(itemId);
        if (itemToUpdate == null || itemToUpdate.getOwnerId() != userId) {
            throw new NotFoundException();
        }
        if (item.getDescription() != null) {
            itemToUpdate.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            itemToUpdate.setAvailable(item.getAvailable());
        }
        if (item.getItemName() != null) {
            itemToUpdate.setItemName(item.getItemName());
        }
        if (item.getRequest() != null) {
            itemToUpdate.setRequest(item.getRequest());
        }
        log.info("Обновлен Item(id = {}, name = {})", item.getItemId(),item.getItemName());
        return itemRepository.update(itemToUpdate);
    }

    @Override
    public List<Item> searchItem(String text) {
        if (text.isEmpty()) {
            return new ArrayList<>();
        }
        return itemRepository.searchItem(text);
    }
}
