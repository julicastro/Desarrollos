package Lambda02;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Definimos un Predicate para verificar si un número es par
        Predicate<Integer> esPar = numero -> numero % 2 == 0;

        // Filtramos la lista utilizando el Predicate
        System.out.println("Números pares:");
        numeros.stream()
                .filter(esPar)
                .forEach(System.out::println);

        predicate();
    }

    public static void predicate() {
        Integer num = 8;
        Predicate<Integer> predicate = n -> n == 8;
        if (predicate.test(num)) {
            System.out.println("true");
            System.out.println(predicate.test(num)); // devuelve true -> por atras es un boolean
        } else {
            System.out.println("false");
            System.out.println(predicate.test(num));
        }
    }


}