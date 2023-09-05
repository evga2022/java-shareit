package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.DuplicateEntityException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.user.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }
    public User getUserById(Integer id){
        return repository.getById(id);
    }
    public User saveUser(User user) {
        if(user.getEmail() == null) {
            throw new ValidationException();
        }
        User userByEmail = repository.getByEmail(user.getEmail());
        if(userByEmail != null) {
            throw new DuplicateEntityException();
        }
        if (user.getName() == null || user.getName().isEmpty() || user.getName().isBlank()){
            user.setName(user.getEmail().split("@")[0]);
        }
        return repository.save(user);
    }
    public User updateUser(Integer userId, User user) {
        User userToUpdate = repository.getById(userId);
        if (userToUpdate == null) {
            throw new NotFoundException();
        }
        if(user.getName() != null && !user.getName().isBlank()) {
            userToUpdate.setName(user.getName());
        }
        if(user.getEmail() != null) {
            User userByEmail = repository.getByEmail(user.getEmail());
            if(userByEmail != null && userByEmail.getId() != userId) {
                throw new DuplicateEntityException();
            }
            userToUpdate.setEmail(user.getEmail());
        }
        return repository.update(userToUpdate);
    }
    public void deleteUser(Integer id){
        repository.delete(id);
    }

}
