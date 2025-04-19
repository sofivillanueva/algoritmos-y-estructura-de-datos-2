package TAD.Lista;

public interface ILista<T> {
    void insertar(T dato);

    int largo();

    boolean existe(T dato);

    T obtener(int pos);

    int obtener(T dato);

    boolean esVacia();

    void eliminar(T dato);

    void eliminar(int pos);
}
