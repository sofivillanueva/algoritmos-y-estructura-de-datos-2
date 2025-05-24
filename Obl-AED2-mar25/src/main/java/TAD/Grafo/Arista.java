package TAD.Grafo;

import TAD.Lista.Lista;
import dominio.Vuelo;
import dominio.VueloPorMinutos;
import dominio.VueloPorPrecio;
import interfaz.TipoVuelo;
import interfaz.TipoVueloPermitido;

public class Arista {
    private boolean existe;
    private int peso;
    private Lista<Vuelo> vuelos;

    public Arista() {
        this.existe = false;
        this.peso = 0;
        this.vuelos = new Lista<>();
    }

    public Arista(int peso) {
        this.peso = peso;
        this.existe = true;
        this.vuelos = new Lista<>();
    }

    public Lista<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(Lista<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public boolean isExiste() {
        return existe;
    }

    public double getPeso(String criterio, TipoVueloPermitido tipoVueloPermitido) {
        if(criterio.equals("minutos")){
            Lista<VueloPorMinutos> vuelosPorMinutos = new Lista<VueloPorMinutos>();
            for (int i = 0; i < this.vuelos.largo(); i++) {
                Vuelo vueloActual = this.vuelos.obtenerPorPos(i);
                if(vueloActual!=null && cumpleTipoVuelo(tipoVueloPermitido, vueloActual))
                    vuelosPorMinutos.insertarOrd(new VueloPorMinutos(vueloActual));
            }
            VueloPorMinutos vueloConMenorMinutos = vuelosPorMinutos.obtenerPorPos(0);
            if(vueloConMenorMinutos==null)
                return Double.MAX_VALUE;
            return vueloConMenorMinutos.getVuelo().getMinutos();
        } else if(criterio.equals("precio")){
            Lista<VueloPorPrecio> vuelosPorPrecio = new Lista<VueloPorPrecio>();
            for (int i = 0; i < this.vuelos.largo(); i++) {
                Vuelo vueloActual = this.vuelos.obtenerPorPos(i);
                if(vueloActual!=null && cumpleTipoVuelo(tipoVueloPermitido, vueloActual)){
                    vuelosPorPrecio.insertarOrd(new VueloPorPrecio(vueloActual));
                }
            }
            VueloPorPrecio vueloConMenorPrecio = vuelosPorPrecio.obtenerPorPos(0);
            if(vueloConMenorPrecio==null)
                return Double.MAX_VALUE;
            return vueloConMenorPrecio.getVuelo().getCostoEnDolares();
        }
        return peso;
    }

    public boolean cumpleTipoVuelo(TipoVueloPermitido tipoVueloPermitido, Vuelo vuelo) {
        return tipoVueloPermitido == TipoVueloPermitido.AMBOS ||
                (tipoVueloPermitido == TipoVueloPermitido.PRIVADO && vuelo.getTipoDeVuelo() == TipoVuelo.PRIVADO) ||
                (tipoVueloPermitido == TipoVueloPermitido.COMERCIAL && vuelo.getTipoDeVuelo() == TipoVuelo.COMERCIAL);
    }
}
