package dominio;

public class Ciudad implements Comparable<Ciudad> {
    private String codigo;
    private String nombre;

    public Ciudad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object obj) {
        Ciudad ciudad = (Ciudad) obj;
        return this.codigo.equals(ciudad.codigo);
    }

    @Override
    public int compareTo(Ciudad ciudad) {
        return this.codigo.compareTo(ciudad.codigo);
    }

    @Override
    public String toString() {
        return this.codigo + ";" + this.nombre;
    }
}
