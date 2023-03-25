package com.github.roddg86.notebook.controller;

import com.github.roddg86.notebook.model.User;
import com.github.roddg86.notebook.model.repository.GBRepository;
import com.github.roddg86.notebook.util.logger.Log;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController {

    private static final Logger LOGGER = Log.log(UserController.class.getName());

    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        LOGGER.log(Level.INFO, "Создан пользователь: " + user.getFirstName() + " " + user.getLastName());
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        LOGGER.log(Level.INFO, "Запрошен пользователь " + userId);
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");

    }

    public User findUserById(long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found."));
    }

    public List<User> getAllUsers() {
        LOGGER.log(Level.INFO, "Запрошены все пользователи");
        return repository.findAll();
    }

    public void updateUser(String userId, User update) {
        LOGGER.log(Level.INFO, "Изменен пользователь " + userId);
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public void deleteUser(String userId) {
        LOGGER.log(Level.INFO, "Пользователь " + userId + " удален");
        repository.delete(Long.parseLong(userId));
    }
}
