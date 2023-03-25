package com.github.roddg86.notebook.model.repository;

import com.github.roddg86.notebook.model.User;

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
    List<String> readAll();
    List<User> findAll();
    User create(User user);
    Optional<User> update(Long userId, User update);
    boolean delete(Long id);
}
