package ru.practicum.shareit.item.model;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.model.ItemRequest;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
public class Item {
    private int itemId;
    private Integer ownerId;
    private String itemName;
    private String description;
    private Boolean available;
    private ItemRequest request;
}
