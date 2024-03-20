package Lambda01;


public class Main {
    public static void main(String[] args) {

        // 1- Pre-Java 8

        Interfaz i = new Interfaz() {
            @Override
            public void m() {
                System.out.println("I::m()");
            }
        };
        i.m();

        /*si bien está prohibido instanciar interfaces lo q está ocurriendo es una forma de crear
        una instancia de una interfaz sin necesidad de crear una clase separada que implemente
        esa interfaz. Se está creando una instancia de la interfaz Lambda02.Interfaz y proporcionando una
        implementación para su método m(). Sin embargo, la diferencia clave es que esta implementación
        se proporciona directamente en el lugar donde se crea la instancia de la interfaz, usando
        una clase anónima. Esto significa que no estás creando una instancia de la interfaz en sí,
        sino que estás creando una instancia de una clase anónima que implementa esa interfaz.
        Con la introducción de las expresiones lambda en Java 8, esta forma de crear instancias
        de interfaces se ha vuelto menos común. Lambda02.Interfaz i = () -> System.out.println("I::m()");
        logra lo mismo q el bloque de código de arriba
        */

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

        ParameterInterface example2 = (a, b) -> a + b; // la parte de a + b es la implementacion del método calculo. puede ser cualquier operacion
        int resultado2 = example2.calculo(5, 8);
        System.out.println(resultado2);

    }
}