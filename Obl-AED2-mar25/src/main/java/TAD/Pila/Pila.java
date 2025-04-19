package TAD.Pila;

public class Pila implements IPila {
    private Nodo top;

    @Override
    public void push(int dato) {
//        Nodo nuevoDato = new Nodo(dato);
//        top.setSig(nuevoDato);
//        this.top = nuevoDato;
        this.top = new Nodo(dato, this.top);

    }

    @Override
    public int pop() {
        int datoRetorno = this.top.getDato();
        this.top = this.top.getSig();
        return datoRetorno;
    }

    @Override
    public int top() {
        return this.top.getDato();
    }

    @Override
    public boolean esta_vacia() {
        return this.top == null;
    }

    @Override
    public boolean esta_llena() {
        return false;
    }
}
