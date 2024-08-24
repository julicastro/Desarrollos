package ejercicios.lista.dinamica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Libreria {

    public static void main(String[] args) {

        LinkedList<Libro> libros = new LinkedList<>();

        Libro l1 = new Libro("Libro 1");
        l1.setVentas(21);
        Libro l2 = new Libro("Libro 2");
        l2.setVentas(23);
        Libro l3 = new Libro("Libro 3");
        l3.setVentas(56);
        Libro l4 = new Libro("Libro 4");
        l4.setVentas(19);
        Libro l5 = new Libro("Libro 5");
        l5.setVentas(22);

        libros.add(l1);
        libros.add(l2);
        libros.add(l3);
        libros.add(l4);
        libros.add(l5);

        System.out.println("Lista de libros original: ");
        libros.forEach(libro -> System.out.println(libro.getTitulo()));
        System.out.println(" ");

        Libro nuevoLibro = new Libro("Nuevo Libro");
        l5.setVentas(22);

        List<Libro> listaOrdenada = libros.stream()
                .sorted(Comparator.comparingInt(Libro::getVentas))
                .collect(Collectors.toList());

        /*
        listaOrdenada.forEach(libro ->
                System.out.println(libro.getVentas()
                ));
         */

        Libro libroRemovido = listaOrdenada.get(0);

        int libroRemovidoIndex = libros.indexOf(libroRemovido);

        libros.remove(libroRemovido);
        libros.add(libroRemovidoIndex, nuevoLibro);

        System.out.println("Lista de libros modificada: ");
        libros.forEach(libro -> System.out.println(libro.getTitulo()));
        System.out.println(" ");










    }







}

