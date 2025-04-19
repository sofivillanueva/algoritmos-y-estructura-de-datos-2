package TAD.AB;

public class AB implements IAb {
    private Nodo raiz;

    @Override
    public int cantNodos() {
        return cantNodosRec(this.raiz);
    }

    private int cantNodosRec(Nodo nodo) {
        if (nodo != null) {
            return 1 + cantNodosRec(nodo.getDer()) + cantNodosRec(nodo.getIzq());
        }
        return 0;
    }

    public int cantNodosHastaNivel(int nivel) {
        return cantNodosHastaNivelRec(this.raiz, nivel, 0);
    }

    private int cantNodosHastaNivelRec(Nodo nodo, int nivel, int nivelActual) {
        if (nodo != null && nivelActual <= nivel) {
            return 1 + cantNodosHastaNivelRec(nodo.getDer(), nivel, nivelActual + 1) + cantNodosHastaNivelRec(nodo.getIzq(), nivel, nivelActual + 1);
        }
        return 0;
    }

    @Override
    public int canthojas() {
        return cantNodosRec(this.raiz);
    }

    private int cantHojasRec(Nodo nodo) {
        if (nodo != null) {
            if (nodo.esHoja()) {
                return 1;
            }
            return cantHojasRec(nodo.getIzq()) + cantHojasRec(nodo.getDer());
        }
        return 0;
    }

    @Override
    public int altura() {
        return alturaRec(this.raiz);
    }

    private int alturaRec(Nodo nodo) {
        if (nodo != null) {
            if (nodo.esHoja()) {
                return 0;
            }
            return 1 + Math.max(alturaRec(nodo.getIzq()), alturaRec(nodo.getDer()));
        }

        return -1;
    }

    @Override
    public boolean iguales(AB aux) {
        return igualesRec(this.raiz, aux.raiz);
    }

    private boolean igualesRec(Nodo nodo1, Nodo nodo2) {
        if (nodo1 != null && nodo2 != null) {
            return (nodo1.getDato() == nodo2.getDato()) &&
                    igualesRec(nodo1.getIzq(), nodo2.getIzq()) &&
                    igualesRec(nodo1.getDer(), nodo2.getDer());
        }
        if (nodo1 == null && nodo2 == null) return true;
        return false;
    }

    @Override
    public boolean equilibrado() {
        return equilibradoRec(this.raiz);
    }

    private boolean equilibradoRec(Nodo nodo) {
        if (nodo != null) {
            return (Math.abs(alturaRec(nodo.getDer()) - alturaRec(nodo.getIzq())) <= 1) &&
                    equilibradoRec(nodo.getIzq()) &&
                    equilibradoRec(nodo.getDer());
        }
        return true;
    }

    @Override
    public boolean pertenece(int dato) {
        return perteneceRec(this.raiz, dato);
    }

    private boolean perteneceRec(Nodo nodo, int dato) {
        if (nodo != null) {
            return nodo.getDato() == dato ||
                    perteneceRec(nodo.getIzq(), dato) ||
                    perteneceRec(nodo.getDer(), dato);
        }
        return false;
    }
}
