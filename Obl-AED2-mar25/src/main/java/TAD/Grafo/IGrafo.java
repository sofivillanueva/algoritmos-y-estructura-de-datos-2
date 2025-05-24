package TAD.Grafo;

import TAD.Lista.Lista;
import dominio.Ciudad;
import dominio.RetornoString;
import interfaz.TipoVueloPermitido;

public interface IGrafo {
    void agregarVertice(Ciudad ciudad);
    boolean existeVertice(Ciudad ciudad);
    void agregarArista(Ciudad ciudadOrigen, Ciudad ciudadDestino, int peso);
    Arista obtenerArista(Ciudad ciudadOrigen, Ciudad ciudadDestino);
    int obtenerPos(Ciudad vertice);
    Lista<Ciudad> bfsConLimiteDeCamino(Ciudad ciudadOrigen, int limite);
    boolean hayConexion(Ciudad ciudadOrigen, Ciudad ciudadDestino);
    boolean hayCaminoBFS(Ciudad ciudadOrigen, Ciudad ciudadDestino);
    RetornoString dijkstra(Ciudad vInicial, Ciudad vFinal, String criterio, TipoVueloPermitido tipoVueloPermitido);
}
