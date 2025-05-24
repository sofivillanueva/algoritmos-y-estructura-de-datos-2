package dominio;

public class VueloPorPrecio implements Comparable<VueloPorPrecio> {
    private Vuelo vuelo;

    public VueloPorPrecio(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    @Override
    public int compareTo(VueloPorPrecio o) {
        return Double.compare(this.vuelo.getCostoEnDolares(), o.vuelo.getCostoEnDolares());
    }
}
