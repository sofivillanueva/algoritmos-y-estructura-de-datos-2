package dominio;

public class ViajeroPorCorreo implements Comparable<ViajeroPorCorreo> {
    private Viajero viajero;

    public ViajeroPorCorreo(Viajero viajero) {
        this.viajero = viajero;
    }

    public Viajero getViajero() {
        return viajero;
    }

    public void setViajero(Viajero viajero) {
        this.viajero = viajero;
    }

    @Override
    public int compareTo(ViajeroPorCorreo viajero) {
        return this.viajero.getCorreo().compareTo(viajero.getViajero().getCorreo());
    }

    @Override
    public boolean equals(Object obj) {
        ViajeroPorCorreo viajeroPorCorreo = (ViajeroPorCorreo) obj;
        return this.viajero.getCorreo().equals(viajeroPorCorreo.getViajero().getCorreo());
    }

    public String toString(){
        return this.viajero.toString();
    }
}
