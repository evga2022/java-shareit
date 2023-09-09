package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.AuthorisationException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final String USER_ID_HEADER = "X-Sharer-User-Id";
    private final ItemService itemService;
    private final UserService userService;

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader(USER_ID_HEADER) Integer userId) {
        checkUserAuthorisation(userId);
        return itemService.getAllItems(userId).stream().map(ItemMapper::toItemDto).collect(Collectors.toList());
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable("itemId") Integer itemId) {
        return ItemMapper.toItemDto(itemService.getItemById(itemId));
    }

    @PostMapping
    public ItemDto saveNewItem(@RequestBody ItemDto itemDto,
                               @RequestHeader(USER_ID_HEADER) Integer userId) {
        checkUserAuthorisation(userId);
        return ItemMapper.toItemDto(itemService.saveItem(ItemMapper.toItem(itemDto), userId));
    }

    @PatchMapping("/{id}")
    public ItemDto updateItem(@PathVariable("id") Integer itemId,
                              @RequestHeader(USER_ID_HEADER) Integer userId,
                              @RequestBody ItemDto itemDto) {
        checkUserAuthorisation(userId);
        return ItemMapper.toItemDto(itemService.updateItem(itemId, userId, ItemMapper.toItem(itemDto)));
    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam String text) {
        return itemService.searchItem(text).stream().map(ItemMapper::toItemDto).collect(Collectors.toList());
    }

    private void checkUserAuthorisation(Integer userId) {
        if (userId == null || userService.getUserById(userId) == null) {
            throw new AuthorisationException();
        }
    }

}
