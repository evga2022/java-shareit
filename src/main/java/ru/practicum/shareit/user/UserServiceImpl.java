package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.DuplicateEntityException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Integer id) {
        return repository.getById(id);
    }

    public User saveUser(User user) {
        if (user.getEmail() == null) {
            throw new ValidationException();
        }
        User userByEmail = repository.getByEmail(user.getEmail());
        if (userByEmail != null) {
            throw new DuplicateEntityException();
        }
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank()) {
            user.setName(user.getEmail().split("@")[0]);
        }
        User savedUser = repository.save(user);
        log.info("Добавлен User(id = {}, name = {}, email = {})", savedUser.getId(), savedUser.getName(), savedUser.getEmail());
        return savedUser;
    }

    public User updateUser(Integer userId, User user) {
        User userToUpdate = repository.getById(userId);
        if (userToUpdate == null) {
            throw new NotFoundException();
        }
        if (user.getName() != null && !user.getName().isBlank()) {
            userToUpdate.setName(user.getName());
        }
        if (user.getEmail() != null) {
            User userByEmail = repository.getByEmail(user.getEmail());
            if (userByEmail != null && userByEmail.getId() != userId) {
                throw new DuplicateEntityException();
            }
            userToUpdate.setEmail(user.getEmail());
        }
        log.info("Обновлен User(id = {}, name = {}, email = {})", userToUpdate.getId(), userToUpdate.getName(), userToUpdate.getEmail());
        return repository.update(userToUpdate);
    }

    public void deleteUser(Integer id) {
        log.info("Обновлен User(id = {})", id);
        repository.delete(id);
    }

}
