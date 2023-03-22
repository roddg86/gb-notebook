package notebook.model.repository;

import notebook.model.User;

import java.util.Optional;

public interface GBRepositoryFindById {
    Optional<User> findById(Long id);
}
