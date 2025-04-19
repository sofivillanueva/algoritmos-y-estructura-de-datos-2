package TAD.Lista;

public class Lista<T> implements ILista<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cant;

    @Override
    public void insertar(T dato) {
        Nodo<T> nuevoDato = new Nodo<>(dato);
        if (this.esVacia()) {
            this.inicio = nuevoDato;
        } else {
            this.fin.setSig(nuevoDato);
        }
        this.fin = nuevoDato;
        cant++;
    }

    @Override
    public int largo() {
        return cant;
    }

    @Override
    public boolean existe(T dato) {
        Nodo<T> aux = this.inicio;
        while (aux.getSig() != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    @Override
    public T obtener(int pos) {
        int posAux = 0;
        Nodo<T> nodoAux = this.inicio;
        if (pos < this.cant) {
            while (nodoAux.getSig() != null && posAux <= pos) {
                if (posAux == pos) {
                    return (T) nodoAux;
                }
                nodoAux = nodoAux.getSig();
                posAux++;
            }
        }

        return null;
    }

    @Override
    public int obtener(T dato) {
        return 0;
    }

    @Override
    public boolean esVacia() {
        return false;
    }

    @Override
    public void eliminar(T dato) {
        Nodo<T> nodoAux = this.inicio;
        if (this.inicio.getDato().equals(dato)) {
            this.inicio = this.inicio.getSig();
        }
        while (nodoAux.getSig() != null) {
            if (nodoAux.getSig().getDato().equals(dato)) {
                nodoAux.setSig(nodoAux.getSig().getSig());
            }
            nodoAux = nodoAux.getSig();
        }

    }

    public void imprimir() {
        Nodo<T> nodoAux = this.inicio;
        System.out.print("-> [ ");
        while (nodoAux != null) {
            System.out.print(nodoAux.getDato());
            if (nodoAux.getSig() != null) {
                System.out.print(" , ");
            }
            nodoAux = nodoAux.getSig();
        }
        System.out.println(" ]");
    }

    @Override
    public void eliminar(int pos) {

    }
}
