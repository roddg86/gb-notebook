package notebook.model.repository;

import notebook.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий, для выполнения CreateReadUpdateDelete (CRUD) операций
 * слой методов для взаимодействия с базой данных
 *
 * @param //<E> тип модели данных
 * @param //<I> тип ID модели данных E
 */
public interface GBRepository {
    List<User> findAll();

    User create(User user);

    Optional<User> findById(Long id);

    Optional<User> update(Long userId, User update);

    boolean delete(Long id);
}
