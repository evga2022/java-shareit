package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.model.ItemRequest;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
public class ItemDto {
    private String name;
    private String description;
    private Boolean available;
    private int id;
    private ItemRequest request;
}
