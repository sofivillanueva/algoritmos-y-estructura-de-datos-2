package TAD.Grafo;

import TAD.Cola.ColaDinamica;
import TAD.Lista.Lista;
import dominio.Ciudad;
import dominio.RetornoString;
import dominio.Vuelo;
import interfaz.TipoVueloPermitido;

public class Grafo implements IGrafo {

    private Ciudad[] vertices;
    private Arista[][] matAdy;
    private boolean esDirigido;
    private int cantActualvertices;
    private int cantMaxVertices;

    public Ciudad[] getVertices() {
        return vertices;
    }

    public Arista[][] getMatAdy() {
        return matAdy;
    }

    public boolean isEsDirigido() {
        return esDirigido;
    }

    public int getCantActualvertices() {
        return cantActualvertices;
    }

    public int getCantMaxVertices() {
        return cantMaxVertices;
    }

    public Grafo(int cantMaxVertices, boolean esDirigido) {
        this.cantMaxVertices = cantMaxVertices;
        this.esDirigido = esDirigido;
        this.cantActualvertices = 0;
        this.vertices = new Ciudad[this.cantMaxVertices];
        this.matAdy = new Arista[this.cantMaxVertices][this.cantMaxVertices];

        if (esDirigido) {
            for (int i = 0; i < this.cantMaxVertices; i++) {
                for (int j = 0; j < this.cantMaxVertices; j++) {
                    this.matAdy[i][j] = new Arista();
                }

            }
        } else {
            for (int i = 0; i < this.cantMaxVertices; i++) {
                for (int j = i; j < this.cantMaxVertices; j++) {
                    this.matAdy[i][j] = new Arista();
                    this.matAdy[j][i] = this.matAdy[i][j];
                }
            }
        }
    }

    private int posLibre() {
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (this.vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void agregarVertice(Ciudad vertice) {
        int posVert = posLibre();
        if (posVert > -1) {
            this.vertices[posVert] = vertice;
            this.cantActualvertices++;
        }
    }

    @Override
    public boolean existeVertice(Ciudad vertice) {
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (this.vertices[i] != null && this.vertices[i].equals(vertice)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int obtenerPos(Ciudad vertice) {
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (this.vertices[i].equals(vertice)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void agregarArista(Ciudad vOrigen, Ciudad vDestino, int peso) {
        int posOrigen = this.obtenerPos(vOrigen);
        int posDestino = this.obtenerPos(vDestino);
        if (posOrigen > -1 && posDestino > -1) {
            Arista nuevaArista = new Arista(peso);
            this.matAdy[posOrigen][posDestino] = nuevaArista;
            if (!this.esDirigido) {
                this.matAdy[posDestino][posOrigen] = nuevaArista;
            }
        }
    }

    @Override
    public Arista obtenerArista(Ciudad verticeOrigen, Ciudad verticeDestino){
        return matAdy[this.obtenerPos(verticeOrigen)][this.obtenerPos(verticeDestino)];
    }

    @Override
    public Lista<Ciudad> bfsConLimiteDeCamino(Ciudad vOrigen, int limite){
        Lista<Ciudad> listaCiudades = new Lista<>();
        ColaDinamica<Integer> colaDinamica = new ColaDinamica<>();
        boolean[] visitados = new boolean[this.cantMaxVertices];
        int[] niveles = new int[this.cantMaxVertices];

        int posVert = this.obtenerPos(vOrigen);
        if (posVert > -1) {
            visitados[posVert] = true;
            listaCiudades.insertarOrd(vOrigen);
            niveles[posVert] = 0;
            colaDinamica.encolar(posVert);
        }

        while (!colaDinamica.esVacia()) {
            int posV = colaDinamica.desencolar();
            int nivelActual = niveles[posV];
            if (nivelActual < limite + 1) {
                for (int i = 0; i < this.cantMaxVertices; i++) {
                    if (this.matAdy[posV][i].isExiste() && !visitados[i]) {
                        Arista arista = obtenerArista(vertices[posV], vertices[i]);
                        if (arista != null && !arista.getVuelos().esVacia()) {
                            int nuevoNivel = nivelActual + 1;
                            if (nuevoNivel <= limite) {
                                niveles[i] = nuevoNivel;
                                visitados[i] = true;
                                listaCiudades.insertarOrd(vertices[i]);
                                colaDinamica.encolar(i);
                            }
                        }
                    }
                }
            }
        }
        return listaCiudades;
    }

    @Override
    public boolean hayConexion(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        int posCiudadOrigen = obtenerPos(ciudadOrigen);
        int posCiudadDestino = obtenerPos(ciudadDestino);

        if (posCiudadOrigen == -1 || posCiudadDestino == -1) {
            return false;
        }

        return this.matAdy[posCiudadOrigen][posCiudadDestino].isExiste();
    }

    @Override
    public boolean hayCaminoBFS(Ciudad ciudadOrigen, Ciudad ciudadDestino) {
        ColaDinamica<Integer> colaDinamica = new ColaDinamica<>();
        boolean[] visitados = new boolean[this.cantMaxVertices];
        int posCiudadOrigen = this.obtenerPos(ciudadOrigen);
        int posCiudadDestino = this.obtenerPos(ciudadDestino);

        if (posCiudadOrigen == -1 || posCiudadDestino == -1) {
            return false;
        }

        visitados[posCiudadOrigen] = true;
        colaDinamica.encolar(posCiudadOrigen);

        while (!colaDinamica.esVacia()) {
            int posV = colaDinamica.desencolar();
            if (posV == posCiudadDestino) {
                return true;
            }
            for (int i = 0; i < this.cantMaxVertices; i++) {
                if (this.matAdy[posV][i].isExiste() && !visitados[i]) {
                    Arista arista = obtenerArista(vertices[posV], vertices[i]);
                    if (arista != null && !arista.getVuelos().esVacia()) {
                        visitados[i] = true;
                        colaDinamica.encolar(i);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public RetornoString dijkstra(Ciudad vInicial, Ciudad vFinal, String criterio, TipoVueloPermitido tipoVueloPermitido) {
        int posInicial = this.obtenerPos(vInicial);
        int posFinal = this.obtenerPos(vFinal);

        double[] costos = new double[this.cantMaxVertices];
        int[] anteriores = new int[this.cantMaxVertices];
        boolean[] visitados = new boolean[this.cantMaxVertices];

        for (int i = 0; i < this.cantMaxVertices; i++) {
            costos[i] = Double.MAX_VALUE;
            anteriores[i] = -1;
            visitados[i] = false;
        }

        costos[posInicial] = 0;
        visitados[posInicial] = true;

        int posVertActual = posInicial;
        while (posVertActual > -1) {
            actualizarCostosAdyacentesNoVisitados(costos, anteriores, visitados, posVertActual, criterio, tipoVueloPermitido);
            posVertActual = obtenerPosVerticeNoVisitadoMenorCosto(costos, visitados, posFinal);
        }

        Lista<String> camino = obtenerCamino(posFinal, anteriores);
        return new RetornoString(camino,costos[posFinal]);
    }

    private Lista<String> obtenerCamino(int posFinal, int[] anteriores) {
        Lista<String> lista = new Lista<>();
        lista.agregarInicio(this.vertices[posFinal].toString());
        int posVerAnterior = anteriores[posFinal];
        while (posVerAnterior > -1) {
            lista.agregarInicio(this.vertices[posVerAnterior].toString());
            posVerAnterior = anteriores[posVerAnterior];
        }
        return lista;
    }

    private int obtenerPosVerticeNoVisitadoMenorCosto(double[] costos, boolean[] visitados, int posFinal) {
        double menorCosto = Double.MAX_VALUE;
        int posVertMenorCosto = -1;
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (!visitados[i] && menorCosto > costos[i]) {
                posVertMenorCosto = i;
                menorCosto = costos[i];
            }
        }

        if (posVertMenorCosto == -1) {
            return -1;
        }

        visitados[posVertMenorCosto] = true;

        if (posVertMenorCosto == posFinal) {
            return -1;
        }
        return posVertMenorCosto;
    }

    private void actualizarCostosAdyacentesNoVisitados(double[] costos, int[] anteriores, boolean[] visitados, int posVerActual, String criterio, TipoVueloPermitido tipoVueloPermitido) {
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (!visitados[i] && this.matAdy[posVerActual][i].isExiste()) {
                double peso = this.matAdy[posVerActual][i].getPeso(criterio, tipoVueloPermitido);
                double costoAcumuladoActual = costos[posVerActual] + peso;
                if (costos[i] > costoAcumuladoActual) {
                    costos[i] = costoAcumuladoActual;
                    anteriores[i] = posVerActual;
                }
            }
        }
    }
}
