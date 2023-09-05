package ru.practicum.shareit.exception;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException{
    String message;
}
