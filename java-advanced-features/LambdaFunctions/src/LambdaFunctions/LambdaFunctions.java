package LambdaFunctions;


import LambdaFunctions.Interfaces.*;
import LambdaFunctions.Interfaces.FunctionalInterface;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.*;

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

    /* FUNCTIONAL INTERFACES */
    /* ------------------------------------------------------------------------------------------- */

    public static void predicate() {
        // metodo test q devuelve booleano
        // boolean test(T t);
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

    public static void supplier() {
        // T get();
        /* sirve para cualquier objeto. su único método get() devuelve lo q nosotros especifiquemos en la impl */
        Supplier<StringBuilder> supSB = StringBuilder::new; // StringBuilder::new == new StringBuilder();
        System.out.println("Supplier SB: " + supSB.get().append("SK")); // ::new hace referencia al constructor. x ende sirve para instanciar

        Supplier<LocalTime> supTime = LocalTime::now; // no funciona el ::new ya q no tiene constructor sin argumentos
        System.out.println("Supplier LocalTime: " + supTime.get());

        Supplier<Double> sRandowm = Math::random;
        System.out.println(sRandowm.get());

        // en resumen Supplier ejecuta lo q está dsp del "=" con su método get();
        // get no recibe argumento y devuelve un resultado
    }

    public static void consumer() {
        /* ser o no ser */
        /* recibe argumento y no devuelve resultado. */
        /* void accept(T t); */
        Consumer<String> printC = s -> System.out.println(s);
        printC.accept("Accept() Requiere un Argumento Pero Get() no");
        // muy util para iterar e imprimir contenido
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Frusciante");
        names.forEach(printC); // este forEach pertence a la clase Iterable y recibe un Consumer. aplica Accept(T t) a cada elemento de la iteración

        List<String> names2 = new ArrayList<>();
        names2.add("Red");
        names2.add("Hot");
        names2.forEach(u -> System.out.println(u)); // compilador entiende q es un consumer x el contexto. x más q no se lo indique explicitamente
    }

    public static void biConsumer() {
        // void accept(T t, U, u) -> clave valor
        // MUY UTIL PARA LOS MAPS
        var mapCapitalCities = new HashMap<String, String>();
        BiConsumer<String, String> biCon = (key, value) -> mapCapitalCities.put(key, value); // si bien accept no tiene return, este .put tiene.
        // le estamos indicando q el metodo accept va a agregar un valor al map mapCapitalCities
        biCon.accept("Argentina", "Buenos Aires");
        biCon.accept("Irlanda", "McGregor");
        // System.out.println(mapCapitalCities); // Argentina=Buenos Aires, Irlanda=McGregor

        BiConsumer<String, String> mapPrint = (k, v) -> System.out.println(k + " is the capital of: " + v);
        // mapCapitalCities.forEach((k, v) -> System.out.println(k + " is the capital of: " + v)); // map tiene este metodo forEach q recibe un BiConsumer
        mapCapitalCities.forEach(mapPrint);
    }

    public static void function() {
        // takes an imput and gives u and output. pueden ser del mismo tipo o distinto
        // R apply(T t)
        // unction<String, Integer> f = Integer::parseInt; ejemplo de funsion para convertir
        Function<String, Integer> f = s -> s.length(); // valor, resultado
        System.out.println(f.apply("Hola"));
    }

    public static void biFunction() {
        // R apply(T t, U u)
        BiFunction<Integer, Integer, Integer> biF = (x, y) -> x + y; // valor, valor, respuesta
        System.out.println(biF.apply(1, 2)); // RESULT = 3

        BiFunction<String, String, String> biF2 = (x, y) -> x.concat(y);
        System.out.println(biF2.apply("Hola", "Hola"));
    }

    public static void myOnwTriFunction() {
        // R apply(T t, U u, V v);
        TriFunction<String, String, Integer, Boolean> triFunction = (a, b, c) -> {
            String concatedText = a.concat(b);
            return concatedText.length() == c;
        };
        System.out.println("TriFunction: " + triFunction.apply("Hola", "Hola", 3)); // false
        System.out.println("TriFunction: " + triFunction.apply("Hola", "Hola", 8)); // true
    }

    public static void unaryOperator() {
        // son muy cercanas a Function y a BiFunction respectivamente.
        // In y Out son del mismo tipo.
        // extends Function
        // son especializaciones de Function
        UnaryOperator<String> unaryOperator = s -> s.toUpperCase(); // String entrada y salida.
        System.out.println(unaryOperator.apply("Hola"));
    }

    public static void binaryOperator() {
        BinaryOperator<String> binaryOperator = (a, b) -> a + b;
        System.out.println(binaryOperator.apply("Hola", "Hola"));
        BinaryOperator<String> binaryOperator2 = (a, b) -> {
            int len = a.length() + b.length();
            return Integer.toString(len);
        };
        System.out.println(binaryOperator2.apply("Hola", "Hola")); // result = 8
    }

    /* FINAL AND 'EFFECTIVELY FINAL' */
    /* ------------------------------------------------------------------------------------------- */

    public static void finalAndEffectivilyFinal(){
        // significa q una variable va a ser final sin especificar la palabra "final"
        // cuando una funcion lambda usa una variable exterior, esta no se puede modificar
        int x = 12;

        Predicate<String> lamda = s -> {
            // x++; no me deja modificar
            // AtomicInteger podría usar en este caso si quiero modificar "x"
            System.out.println("x == " + x);
            return s.isEmpty() && x % 2 == 0;
        };
        System.out.println(lamda.test("a"));
        // tampoco me deja modificar la variable dsp x++;
        LambdaEffectivelyFinal lambdaEffectivelyFinal = new LambdaEffectivelyFinal();
        lambdaEffectivelyFinal.lambdaEffectivelyFinalTest();
        // no hay problema con instancias de clase
    }

    /* REFERENCED METHOD */
    /* ------------------------------------------------------------------------------------------- */

    public static void referenceredMethod(){
        List<String> names = Arrays.asList("Sean", "Mary", "John", "Arthur");
        names.forEach(name -> System.out.println(name)); // recordar q este forEach recibe un consumer. viene de la clase List q hereda de Iterable.
        names.forEach(System.out::println); // significa lo mismo q la linea de arriba.
    }

    public static void boundMethodReferenced(){
        // Vinculado
        /* en base a una instancia de objeto conocida al momento de compilar */
        String name = "John";

        // Supplier<T> -> T get()
        Supplier <String> sup = () -> name.toUpperCase(); // lambda
        Supplier<String> sup2 = name::toUpperCase; // method referenced a una instancia particular
        System.out.println(sup.get());
        System.out.println(sup2.get());

        // en el siguiente ejemplo es importante el contexto.
        Predicate<String> titulo1 = (titulo) -> name.startsWith(titulo);
        Predicate<String> titulo2 = name::startsWith; // no le indico el parametro titulo pero x contexto el compilador lo entiende.

        System.out.println(titulo1.test("Ma")); // false.
        System.out.println(titulo2.test("Jo")); // true. busca el argumento aunque no se lo hayamos inidicado.
    }

    public static void unboundMethodReferenced(){
        // No Vinculado
        /* en base a una instancia de objeto conocida al momento de ejecutar */
    }


    public static void name(){
    }

    public static void main(String[] args) {
        unboundMethodReferenced();
    }


}