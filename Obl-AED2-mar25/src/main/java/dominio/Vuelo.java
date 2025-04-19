package dominio;

import interfaz.TipoVuelo;

public class Vuelo {
    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private String codigoDeVuelo;
    private double combustible;
    private double minutos;
    private double costoEnDolares;
    private TipoVuelo tipoDeVuelo;

    public Vuelo(Ciudad ciudadOrigen, Ciudad ciudadDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, TipoVuelo tipoDeVuelo) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.codigoDeVuelo = codigoDeVuelo;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
        this.tipoDeVuelo = tipoDeVuelo;
    }

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public void setCodigoDeVuelo(String codigoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;
    }

    public double getCombustible() {
        return combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public double getMinutos() {
        return minutos;
    }

    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }

    public void setCostoEnDolares(double costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public TipoVuelo getTipoDeVuelo() {
        return tipoDeVuelo;
    }

    public void setTipoDeVuelo(TipoVuelo tipoDeVuelo) {
        this.tipoDeVuelo = tipoDeVuelo;
    }
}
