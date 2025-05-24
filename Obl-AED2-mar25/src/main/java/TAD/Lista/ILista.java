package TAD.Lista;

public interface ILista<T> {
    void insertar(T dato);
    void insertarOrd(T dato);
    void agregarInicio(T dato);
    void agregarFinal(T dato);
    int largo();
    boolean existe(T dato);
    T obtenerPorPos(int pos);
    boolean esVacia();
    String listaComoTexto();
}
