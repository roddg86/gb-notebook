package notebook.controller;

import notebook.model.User;
import notebook.model.repository.*;
import notebook.util.logger.Log;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController implements UserControllerSaveUser, UserControllerReadUser, UserControllerFindUserById, UserControllerGetAllUsers, UserControllerUpdateUser, UserControllerDeleteUser {

    private static final Logger LOGGER = Log.log(UserController.class.getName());


    //private final GBRepository repository;
    private final GBRepositoryCreate repositoryCreate;
    private final GBRepositoryDelete repositoryDelete;
    private final GBRepositoryFindAll repositoryFindAll;
    private final GBRepositoryFindById repositoryFindById;
    private final GBRepositoryUpdate repositoryUpdate;

//    public UserController(GBRepository repository) {
//        this.repository = repository;
//    }


    public UserController(GBRepositoryCreate repositoryCreate, GBRepositoryDelete repositoryDelete, GBRepositoryFindAll repositoryFindAll, GBRepositoryFindById repositoryFindById, GBRepositoryUpdate repositoryUpdate) {
        this.repositoryCreate = repositoryCreate;
        this.repositoryDelete = repositoryDelete;
        this.repositoryFindAll = repositoryFindAll;
        this.repositoryFindById = repositoryFindById;
        this.repositoryUpdate = repositoryUpdate;
    }

    @Override
    public void saveUser(User user) {
        LOGGER.log(Level.INFO, "Создан пользователь: " + user.getFirstName() + " " + user.getLastName());
        repositoryCreate.create(user);
    }

    @Override
    public User readUser(Long userId) throws Exception {
        LOGGER.log(Level.INFO, "Запрошен пользователь " + userId);
        List<User> users = repositoryFindAll.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");

    }

    @Override
    public User findUserById(long id) {
        return repositoryFindById.findById(id).orElseThrow(()-> new RuntimeException("User not found."));
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.log(Level.INFO, "Запрошены все пользователи");
        return repositoryFindAll.findAll();
    }

    @Override
    public void updateUser(String userId, User update) {
        LOGGER.log(Level.INFO, "Изменен пользователь " + userId);
        update.setId(Long.parseLong(userId));
        repositoryUpdate.update(Long.parseLong(userId), update);
    }

    @Override
    public void deleteUser(String userId) {
        LOGGER.log(Level.INFO, "Пользователь " + userId + " удален");
        repositoryDelete.delete(Long.parseLong(userId));
    }
}
