package ru.practicum.shareit.user.model;

import lombok.Builder;
import lombok.Data;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
public class User {
    private int id;
    private String name;
    private String email;
}
