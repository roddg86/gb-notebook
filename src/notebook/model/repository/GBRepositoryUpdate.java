package notebook.model.repository;

import notebook.model.User;

import java.util.Optional;

public interface GBRepositoryUpdate {
    Optional<User> update(Long userId, User update);
}
