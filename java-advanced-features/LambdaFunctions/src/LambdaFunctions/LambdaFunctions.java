package LambdaFunctions;


import LambdaFunctions.Interfaces.FunctionalInterface;
import LambdaFunctions.Interfaces.Interfaz;
import LambdaFunctions.Interfaces.ParameterInterface;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaFunctions {

    public static void lambda01() {
        // 1- Pre-Java 8
        Interfaz i = new Interfaz() {
            @Override
            public void m() {
                System.out.println("I::m()");
            }
        };
        i.m();
        // Java 8 - Lambda Expression
        Interfaz lambdaI = () -> {
            System.out.println("Lambda Version");
        };
        lambdaI.m();
        Interfaz lambda2 = () -> System.out.println("Lambda Version 2");
        lambda2.m();
        // Java 8 - Lambda con parametro
        ParameterInterface example1 = (a, b) -> {
            return 38 + 17;
        };
        int resultado = example1.calculo(0, 0);
        System.out.println("El resultado de la operación es: " + resultado);
        ParameterInterface example2 = (a, b) -> a + b; // la parte de "(a + b)" es la implementacion del método calculo. puede ser cualquier operacion
        int resultado2 = example2.calculo(5, 8);
        System.out.println(resultado2);
    }

    public static void lambda02() {
        FunctionalInterface<Integer> lambda = i -> i < 0; // i es x la T. solo 1 argumento. el resto es la lógica. es decir, la implementación.
        // FunctionalInterface<Integer> lambda = i -> {return i < 0;}: -> esta forma tmb es correcta
        System.out.println("FunctionalInterface: " + lambda.isNegative(-1));
        System.out.println("FunctionalInterface: " + lambda.isNegative(+1));

        Predicate<Integer> predicate = i -> i < 0;
        System.out.println("FunctionalInterface: " + predicate.test(-1));
        System.out.println("FunctionalInterface: " + predicate.test(+1));
    }

    public static void predicate() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Definimos un Predicate para verificar si un número es par
        Predicate<Integer> esPar = numero -> numero % 2 == 0;

        // Filtramos la lista utilizando el Predicate
        System.out.println("Números pares:");
        numeros.stream()
                .filter(esPar)
                .forEach(System.out::println);
    }

    public static void predicate01() {
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

    private static <T> boolean check(T t, Predicate<T> lamda) {
        return lamda.test(t);
    }

    public static void predicate02() {
        int x = 4;
        System.out.println("Is " + x + " even? " + check(4, n -> n % 2 == 0)); // true
        /* el primer argumento, o sea el 4. es el q va a remplazar a la n. el 2do argumento es la condición en base a n */
        /*  n -> n % 2 == 0 es el test() method */
        x = 7;
        System.out.println("Is " + x + " even? " + check(7, y -> y % 2 == 0)); // false
    }

    public static void predicate03() {
        String s = "Hola, como estas?";
        System.out.println("Is " + s + " even starts with 'Ho'? " + check("Hola, como estas", a -> a.startsWith("Ho"))); // true
        /* el primer argumento, o sea el 4. es el q va a remplazar a la n. el 2do argumento es la condición en base a n */
    }

    public static void biPredicate() {
        // 2 tipos genericos
        BiPredicate<String, Integer> checkLenght = (str, len) -> str.length() == len;
        System.out.println(checkLenght.test("Hola", 4));
    }

    public static void supplier(){
        /* sirve para cualquier objeto. su único método get() devuelve lo q nosotros especifiquemos en la impl */
        Supplier<StringBuilder> supSB = StringBuilder::new; // StringBuilder::new == new StringBuilder();
        System.out.println("Supplier SB: " + supSB.get().append("SK")); // ::new hace referencia al constructor. x ende sirve para instanciar

        Supplier<LocalTime> supTime = LocalTime::now; // no funciona el ::new ya q no tiene constructor sin argumentos
        System.out.println("Supplier LocalTime: " + supTime.get());

        Supplier<Double> sRandowm = Math::random;
        System.out.println(sRandowm.get());

        // en resument Supplier ejecuta lo q está dsp del "=" con su método get();
    }

    public static void consumerAndBiConsumer(){

    }

    public static void main(String[] args) {
        consumerAndBiConsumer();
    }


}