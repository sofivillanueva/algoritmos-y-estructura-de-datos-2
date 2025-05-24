package dominio;

import interfaz.Categoria;

import java.util.regex.Pattern;

public class Viajero implements Comparable<Viajero> {
    private String cedula;
    private String nombre;
    private String correo;
    private int edad;
    private Categoria categoria;

    public Viajero(){}

    public Viajero(String cedula, String nombre, String correo, int edad, Categoria unaCategoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.categoria = unaCategoria;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int compareTo(Viajero viajero) {
        String cedula1 = this.cedula.replace(".", "").replace("-", "");
        String cedula2 = viajero.getCedula().replace(".", "").replace("-", "");

        long c1 = Long.parseLong(cedula1);
        long c2 = Long.parseLong(cedula2);

        int resultado = Long.compare(c1, c2);
        return resultado;
    }

    @Override
    public boolean equals(Object obj) {
        Viajero viajero = (Viajero) obj;
        return this.cedula.equals(viajero.cedula);
    }

    public String toString(){
        return this.cedula + ";" +
                this.nombre + ";" +
                this.correo + ";" +
                this.edad + ";" +
                (this.categoria != null ? this.categoria.getTexto() : "");
    }
}
