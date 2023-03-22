package notebook.model.dao;

import java.util.List;

public interface OperationSaveAll<T> {
    void saveAll(List<T> data);
}
