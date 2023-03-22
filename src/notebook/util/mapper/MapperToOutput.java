package notebook.util.mapper;

public interface MapperToOutput<E, T> {
    E toOutput(T t);
}
