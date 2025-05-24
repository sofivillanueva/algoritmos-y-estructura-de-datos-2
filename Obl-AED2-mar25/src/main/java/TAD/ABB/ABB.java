package TAD.ABB;
import TAD.Lista.Lista;

public class ABB<T extends Comparable<T>> implements IAbb<T> {
    NodoABB<T> raiz;
    int cantNodosBuscados;

    public int getCantNodosBuscados() {
        return cantNodosBuscados;
    }

    @Override
    public void insertar(T dato) {
        if (this.raiz == null) {
            this.raiz = new NodoABB<>(dato);
        } else {
            insertarRec(this.raiz, dato);
        }
    }

    private void insertarRec(NodoABB<T> nodo, T dato) {
        if (nodo != null) {
            if (dato.compareTo(nodo.getDato()) <= 0) {
                if (nodo.getIzq() == null) {
                    nodo.setIzq(new NodoABB<>(dato));
                } else {
                    insertarRec(nodo.getIzq(), dato);
                }
            } else {
                if (nodo.getDer() == null) {
                    nodo.setDer(new NodoABB<>(dato));
                } else {
                    insertarRec(nodo.getDer(), dato);
                }
            }
        }
    }

    @Override
    public Lista<String> generarListaAsc() {
        Lista<String> listaDeElementos = new Lista<>();
        return generarListaAsc(this.raiz, listaDeElementos);
    }

    private Lista<String> generarListaAsc(NodoABB<T> nodo, Lista<String> elementosAGuardar) {
        if (nodo != null) {
            generarListaAsc(nodo.getIzq(), elementosAGuardar);
            elementosAGuardar.insertar(nodo.getDato().toString());
            generarListaAsc(nodo.getDer(), elementosAGuardar);
        }
        return elementosAGuardar;
    }

    @Override
    public Lista<String> generarListaDesc() {
        Lista<String> listaDeElementos = new Lista<>();
        return generarListaDescRec(this.raiz, listaDeElementos);
    }

    private Lista<String> generarListaDescRec(NodoABB<T> nodo, Lista<String> elementosAGuardar) {
        if (nodo != null) {
            generarListaDescRec(nodo.getDer(), elementosAGuardar);
            elementosAGuardar.insertar(nodo.getDato().toString());
            generarListaDescRec(nodo.getIzq(), elementosAGuardar);
        }
        return elementosAGuardar;
    }

    @Override
    public T buscar(T dato) {
        this.cantNodosBuscados = 0;
        return buscarRec(this.raiz, dato);
    }

    private T buscarRec(NodoABB<T> nodo, T dato) {
        if (nodo != null) {
            cantNodosBuscados++;
            if (nodo.getDato().equals(dato)) {
                return nodo.getDato();
            } else if (dato.compareTo(nodo.getDato()) < 0) {
                return buscarRec(nodo.getIzq(), dato);
            }
            return buscarRec(nodo.getDer(), dato);
        }
        return null;
    }
}
