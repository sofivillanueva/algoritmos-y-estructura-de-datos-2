package TAD.Lista;

public class Lista<T extends Comparable<T>> implements ILista<T> {
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
    public void insertarOrd(T dato) {
        if (esVacia() || inicio.getDato().compareTo(dato) > 0) {
            agregarInicio(dato);
        } else {
            if (fin.getDato().compareTo(dato) < 0) {
                agregarFinal(dato);
            } else {
                Nodo<T> aux = inicio;
                while (aux.getSig() != null && aux.getSig().getDato().compareTo(dato) < 0) {
                    aux = aux.getSig();
                }
                Nodo<T> nuevo = new Nodo<>(dato);
                nuevo.setSig(aux.getSig());
                aux.setSig(nuevo);
                cant++;
            }
        }
    }

    @Override
    public void agregarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSig(inicio);
            inicio = nuevo;
        }
        cant++;
    }

    @Override
    public void agregarFinal(T dato) {
        if (esVacia()) {
            agregarInicio(dato);
        } else {
            Nodo<T> nuevo = new Nodo<>(dato);
            fin.setSig(nuevo);
            fin = nuevo;
            cant++;
        }
    }

    @Override
    public int largo() {
        return cant;
    }

    @Override
    public boolean existe(T dato) {
        Nodo<T> aux = this.inicio;
        if (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            } else {
                while (aux.getSig() != null) {
                    if (aux.getDato().equals(dato)) {
                        return true;
                    }
                    aux = aux.getSig();
                }
            }
        }
        return false;
    }

    @Override
    public T obtenerPorPos(int pos) {
        int posAux = 0;
        Nodo<T> nodoAux = this.inicio;
        if (pos < this.cant) {
            while (nodoAux.getSig() != null && posAux <= pos) {
                if (posAux == pos) {
                    return (T) nodoAux.getDato();
                }
                nodoAux = nodoAux.getSig();
                posAux++;
            }
        }
        if (posAux == pos && nodoAux != null) {
            return (T) nodoAux.getDato();
        }
        return null;
    }

    @Override
    public boolean esVacia() {
        return this.inicio == null;
    }

    @Override
    public String listaComoTexto() {
        Nodo<T> aux = this.inicio;
        String resultado = "";
        while (aux != null) {
            if (aux.getSig() != null) {
                resultado += aux.getDato() + "|";
            } else {
                resultado += aux.getDato();
            }
            aux = aux.getSig();
        }
        return resultado;
    }
}
