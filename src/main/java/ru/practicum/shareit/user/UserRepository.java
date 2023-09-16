package ru.practicum.shareit.user;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User save(User user);

    User getById(Integer id);

    User getByEmail(String email);

    User update(User user);

    void delete(Integer id);
}
