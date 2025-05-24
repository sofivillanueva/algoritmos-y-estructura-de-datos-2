package TAD.ABB;

import TAD.Lista.Lista;

public interface IAbb<T> {
    void insertar(T dato);
    Lista<String> generarListaAsc();
    Lista<String> generarListaDesc();
    T buscar(T dato);
}
