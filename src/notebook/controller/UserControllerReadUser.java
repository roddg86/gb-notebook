package notebook.controller;

import notebook.model.User;

public interface UserControllerReadUser {
    User readUser(Long userId) throws Exception;
}
