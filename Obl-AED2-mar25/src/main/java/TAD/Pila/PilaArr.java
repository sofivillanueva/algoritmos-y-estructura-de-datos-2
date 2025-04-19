package TAD.Pila;

public class PilaArr implements IPila {
    private int[] datos;
    private int indice_top;

    public PilaArr(int cantMax) {
        this.datos = new int[cantMax];
        this.indice_top = -1;
    }

    @Override
    public void push(int dato) {
//        this.indice_top+=1;
//        this.datos[this.indice_top]=dato;
        this.datos[++this.indice_top] = dato;
    }

    @Override
    public int pop() {
//        int datoRetorno=this.datos[this.indice_top];
//        this.indice_top-=1;
//        return datoRetorno;

        return this.datos[this.indice_top--];
    }

    @Override
    public int top() {
        return this.datos[this.indice_top];
    }

    @Override
    public boolean esta_vacia() {
        return this.indice_top == -1;
    }

    @Override
    public boolean esta_llena() {
        return this.indice_top == this.datos.length - 1;
    }
}
