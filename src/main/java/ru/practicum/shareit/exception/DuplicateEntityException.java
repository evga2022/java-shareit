package ru.practicum.shareit.exception;

import lombok.Data;

@Data
public class DuplicateEntityException extends RuntimeException{
    String message;
}
