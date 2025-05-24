package TAD.Cola;

public interface ICola<T> {
    void encolar(T dato);
    T desencolar();
    boolean esVacia();
}
