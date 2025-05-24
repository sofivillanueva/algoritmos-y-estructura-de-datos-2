package dominio;

import TAD.Lista.Lista;

public class RetornoString {
    private Lista<String> lista;
    private double cantidadResultado;

    public RetornoString(Lista<String> lista, double cantidadResultado) {
        this.lista = lista;
        this.cantidadResultado = cantidadResultado;
    }

    public Lista<String> getLista() {
        return lista;
    }

    public void setLista(Lista<String> lista) {
        this.lista = lista;
    }

    public int getCantidadResultado() {
        return (int)cantidadResultado;
    }

    public void setCantidadResultado(double cantidadResultado) {
        this.cantidadResultado = cantidadResultado;
    }
}
