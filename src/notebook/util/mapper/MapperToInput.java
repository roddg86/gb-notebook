package notebook.util.mapper;

public interface MapperToInput<E, T> {
    T toInput(E e);
}
