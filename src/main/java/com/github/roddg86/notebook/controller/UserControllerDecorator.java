package com.github.roddg86.notebook.controller;

import com.github.roddg86.notebook.model.User;
import com.github.roddg86.notebook.model.repository.GBRepository;
import com.github.roddg86.notebook.util.log.Log;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class UserControllerDecorator {

    private final GBRepository repository;

    private final Log consoleLog;

    private final Log fileLog;


    public UserControllerDecorator(GBRepository repository, Log consoleLog, Log fileLog) {
        this.repository = repository;
        this.consoleLog = consoleLog;
        this.fileLog = fileLog;
    }

    public void saveUser(User user) {
        consoleLog.log("Создан пользователь: " + user.getFirstName() + " " + user.getLastName());
        fileLog.log(UserControllerDecorator.class.getName()).log(Level.INFO, "Создан пользователь: " + user.getFirstName() + " " + user.getLastName());
        repository.create(user);
    }

    public User findUserById(Long userId) throws Exception {
        consoleLog.log("Запрошен пользователь " + userId);
        fileLog.log(UserControllerDecorator.class.getName()).log(Level.INFO, "Запрошен пользователь " + userId);
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
        fileLog.log(UserControllerDecorator.class.getName()).log(Level.INFO, "Запрошены все пользователи");
        return repository.findAll();
    }

    public void updateUser(String userId, User update) {
        consoleLog.log("Изменен пользователь " + userId);
        fileLog.log(UserControllerDecorator.class.getName()).log(Level.INFO, "Изменен пользователь " + userId);
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public void deleteUser(String userId) {
        consoleLog.log("Пользователь " + userId + " удален");
        fileLog.log(UserControllerDecorator.class.getName()).log(Level.INFO, "Пользователь " + userId + " удален");
        repository.delete(Long.parseLong(userId));
    }
}
