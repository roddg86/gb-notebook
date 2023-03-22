package notebook;

import notebook.controller.UserController;
import notebook.model.dao.impl.FileOperation;
import notebook.model.repository.*;
import notebook.model.repository.impl.UserRepository;
import notebook.view.UserView;

import static notebook.util.DBConnector.DB_PATH;
import static notebook.util.DBConnector.createDB;

public class Main {
    public static void main(String[] args) {
        createDB();
        FileOperation fileOperation = new FileOperation(DB_PATH);

        GBRepositoryCreate repositoryCreate = new UserRepository(fileOperation);
        GBRepositoryDelete repositoryDelete = new UserRepository(fileOperation);
        GBRepositoryFindAll repositoryFindAll = new UserRepository(fileOperation);
        GBRepositoryFindById repositoryFindById = new UserRepository(fileOperation);
        GBRepositoryUpdate repositoryUpdate = new UserRepository(fileOperation);

        UserController controller = new UserController(repositoryCreate, repositoryDelete,repositoryFindAll,repositoryFindById,repositoryUpdate);
        UserView view = new UserView(controller);
        view.run();
    }
}
