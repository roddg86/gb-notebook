package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.io.FileInputStream;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UserController {

    static Logger LOGGER;
    static {
        try (FileInputStream ins = new FileInputStream("src/notebook/util/logger/log.config")) { // полный путь до файла с конфигами
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(UserController.class.getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
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
