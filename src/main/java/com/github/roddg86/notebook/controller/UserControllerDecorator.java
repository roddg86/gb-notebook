package com.github.roddg86.notebook.controller;

import com.github.roddg86.notebook.model.User;
import com.github.roddg86.notebook.model.repository.GBRepository;
import com.github.roddg86.notebook.util.log.Logger;

import java.util.List;
import java.util.Objects;

public class UserControllerDecorator {
    private final GBRepository repository;

    private final Logger logger;

    public UserControllerDecorator(GBRepository repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    public void saveUser(User user) {
        logger.log("Создан пользователь: " + user.getFirstName() + " " + user.getLastName());
        repository.create(user);
    }

    public User findUserById(Long userId) throws Exception {
        logger.log("Запрошен пользователь " + userId);
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }

    public List<User> getAllUsers() {
        logger.log("Запрошены все пользователи");
        return repository.findAll();
    }

    public void updateUser(String userId, User update) {
        logger.log("Изменен пользователь " + userId);
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public void deleteUser(String userId) {
        logger.log("Пользователь " + userId + " удален");
        repository.delete(Long.parseLong(userId));
    }
}
