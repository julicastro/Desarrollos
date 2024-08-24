package ejercicios.lista.dinamica;

public class Libro {

    private String titulo;
    private int ventas;

    public Libro(String titulo) {
        this.titulo = titulo;
    }

    public Libro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }
}
