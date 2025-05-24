package dominio;

public class ViajeroPorEdad implements Comparable<ViajeroPorEdad> {
    private Viajero viajero;

    public ViajeroPorEdad(Viajero viajero) {
        this.viajero = viajero;
    }

    public Viajero getViajero() {
        return viajero;
    }

    public void setViajero(Viajero viajero) {
        this.viajero = viajero;
    }

    @Override
    public int compareTo(ViajeroPorEdad viajero) {
        return this.viajero.getEdad() - viajero.getViajero().getEdad();
    }

    public String toString(){
        return this.viajero.getCedula() + ";" +
                this.viajero.getNombre() + ";" +
                this.viajero.getCorreo() + ";" +
                this.viajero.getEdad() + ";" +
                (this.viajero.getCategoria() != null ? this.viajero.getCategoria().getTexto() : "");
    }
}
