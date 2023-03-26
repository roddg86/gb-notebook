package com.github.roddg86.notebook;

import com.github.roddg86.notebook.controller.UserControllerDecorator;
import com.github.roddg86.notebook.model.repository.GBRepository;
import com.github.roddg86.notebook.model.repository.impl.UserRepository;
import com.github.roddg86.notebook.util.log.impl.ConsoleLogger;
import com.github.roddg86.notebook.util.mapper.impl.UserMapper;
import com.github.roddg86.notebook.view.UserViewWithLog;

import static com.github.roddg86.notebook.util.DBConnector.DB_PATH;
import static com.github.roddg86.notebook.util.DBConnector.createDB;

public class Main {
    public static void main(String[] args) {
        createDB();
        GBRepository repository = new UserRepository(new UserMapper(),DB_PATH);
//        UserController controller = new UserController(repository);
//        UserView view = new UserView(controller);
        UserControllerDecorator controller = new UserControllerDecorator(repository, new ConsoleLogger());
        UserViewWithLog view = new UserViewWithLog(controller);
        view.run();
    }
}
