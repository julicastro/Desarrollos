package Lambda02;

import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        FunctionalInterface<Integer> lambda = i -> i < 0; // i es x la T. solo 1 argumento. el resto es la lógica. es decir, la implementación.
        // FunctionalInterface<Integer> lambda = i -> {return i < 0;}: -> esta forma tmb es correcta
        System.out.println("FunctionalInterface: " + lambda.isNegative(-1));
        System.out.println("FunctionalInterface: " + lambda.isNegative(+1));

        Predicate<Integer> predicate = i -> i < 0;
        System.out.println("FunctionalInterface: " + predicate.test(-1));
        System.out.println("FunctionalInterface: " + predicate.test(+1));

    }
}