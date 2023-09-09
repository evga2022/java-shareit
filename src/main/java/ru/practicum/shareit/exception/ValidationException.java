package ru.practicum.shareit.exception;

import lombok.Data;

public class ValidationException extends RuntimeException {
    String message;
}
