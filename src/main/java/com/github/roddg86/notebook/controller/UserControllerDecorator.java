package com.github.roddg86.notebook.controller;

import com.github.roddg86.notebook.model.User;
import com.github.roddg86.notebook.model.repository.GBRepository;
import com.github.roddg86.notebook.util.log.Log;

import java.util.List;
import java.util.Objects;

public class UserControllerDecorator {

    private final GBRepository repository;

    private final Log consoleLog;


    public UserControllerDecorator(GBRepository repository, Log consoleLog) {
        this.repository = repository;
        this.consoleLog = consoleLog;

    }

    public void saveUser(User user) {
        consoleLog.log("Создан пользователь: " + user.getFirstName() + " " + user.getLastName());
        repository.create(user);
    }

    public User findUserById(Long userId) throws Exception {
        consoleLog.log("Запрошен пользователь " + userId);
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }

    public List<User> getAllUsers() {
        consoleLog.log("Запрошены все пользователи");
        return repository.findAll();
    }

    public void updateUser(String userId, User update) {
        consoleLog.log("Изменен пользователь " + userId);
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public void deleteUser(String userId) {
        consoleLog.log("Пользователь " + userId + " удален");
        repository.delete(Long.parseLong(userId));
    }
}
