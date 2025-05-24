package dominio;

public class VueloPorMinutos implements Comparable<VueloPorMinutos> {
    private Vuelo vuelo;

    public VueloPorMinutos(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    @Override
    public int compareTo(VueloPorMinutos o) {
        return Double.compare(this.vuelo.getMinutos(), o.vuelo.getMinutos());
    }
}
