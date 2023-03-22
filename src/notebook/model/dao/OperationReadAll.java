package notebook.model.dao;

import java.util.List;

public interface OperationReadAll<T> {
    List<T> readAll();
}
