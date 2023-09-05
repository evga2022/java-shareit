package ru.practicum.shareit.user;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository{
    List<User> users = new ArrayList<>();
    Integer nextId = 1;

    @Override
    public List<User> findAll() {
        return users;
    }

    private Integer getNewId(){
        return nextId++;
    }

    @Override
    public User save(User user) {
        user.setId(getNewId());
        users.add(user);
        return user;
    }

    @Override
    public User getById(Integer id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User update(User user) {
        delete(user.getId());
        users.add(user);
        return user;
    }

    @Override
    public void delete(Integer id) {
        users = users.stream().filter(user -> user.getId() != id).collect(Collectors.toList());
    }
}
