package ru.practicum.shareit.exception;

import lombok.Data;

@Data
public class AuthorisationException extends RuntimeException {
    String message;
}
