package TAD.Pila;

public class Nodo {
    
    private int dato;
    private Nodo sig;
    
    public Nodo(int dato) {
        this.dato = dato;
        this.sig = null;
    }

    public Nodo(int dato, Nodo sig) {
        this.dato = dato;
        this.sig = sig;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }
}
