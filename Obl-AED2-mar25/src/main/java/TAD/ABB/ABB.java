package TAD.ABB;
import TAD.Lista.Lista;

public class ABB<T extends Comparable<T>> implements IAbb<T> {
    NodoABB<T> raiz;

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
                //caso IZQ
                if (nodo.getIzq() == null) {
                    nodo.setIzq(new NodoABB<>(dato));
                } else {
                    insertarRec(nodo.getIzq(), dato);
                }
            } else {
                //caso DER
                if (nodo.getDer() == null) {
                    nodo.setDer(new NodoABB<>(dato));
                } else {
                    insertarRec(nodo.getDer(), dato);
                }
            }
        }
    }

    @Override
    public boolean pertenece(T dato) {
        return perteneceRec(this.raiz, dato);
    }

    private boolean perteneceRec(NodoABB<T> nodo, T dato) {
        if (nodo != null) {
            if (nodo.getDato().equals(dato)) {
                return true;
            } else if (dato.compareTo(nodo.getDato()) < 0) {
                return perteneceRec(nodo.getIzq(), dato);
            }
            return perteneceRec(nodo.getDer(), dato);
        }
        return false;
    }

    @Override
    public void imprimirAsc() {
        imprimirAscRec(this.raiz);
    }

    private void imprimirAscRec(NodoABB<T> nodo) {
        if (nodo != null) {
            imprimirAscRec(nodo.getIzq());
            System.out.print(nodo.getDato() + " - ");
            imprimirAscRec(nodo.getDer());
        }
    }

    @Override
    public void imprimirDsc() {
        imprimirDesRec(this.raiz);
    }

    private void imprimirDesRec(NodoABB<T> nodo) {
        if (nodo != null) {
            imprimirDesRec(nodo.getDer());
            System.out.print(nodo.getDato() + " - ");
            imprimirDesRec(nodo.getIzq());
        }
    }

    @Override
    public void eliminarMinimo() {
        //TODO
    }


    public int cantMayoresA(T k) {
        return cantMayoresARec(this.raiz, k);
    }

    private int cantMayoresARec(NodoABB<T> nodo, T k) {
        if (nodo != null) {
            if (k.compareTo(nodo.getDato()) < 0) {
                return 1 + cantMayoresARec(nodo.getIzq(), k) + cantMayoresARec(nodo.getDer(), k);
            }
            return cantMayoresARec(nodo.getDer(), k);
        }
        return 0;
    }
    
    
    public Lista<T> elemMayoresA(T k){
        Lista<T> mayoresA = new Lista<>();
        elemMayoresARec(this.raiz,k,mayoresA);
        return mayoresA;
    }
    
    private void elemMayoresARec(NodoABB<T> nodo, T k,Lista<T> instLista){
        if(nodo!=null){
            if (k.compareTo(nodo.getDato()) < 0) {
                elemMayoresARec(nodo.getIzq(),k,instLista);
                instLista.insertar(nodo.getDato());
                elemMayoresARec(nodo.getDer(),k,instLista);
            }else{
                elemMayoresARec(nodo.getDer(),k,instLista);
            }
        }
    }
}
