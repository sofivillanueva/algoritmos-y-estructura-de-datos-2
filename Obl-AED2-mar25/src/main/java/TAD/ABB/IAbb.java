package TAD.ABB;

public interface IAbb<T> {
    void insertar(T dato);

    boolean pertenece(T dato);

    void imprimirAsc();

    void imprimirDsc();

    void eliminarMinimo();

}
