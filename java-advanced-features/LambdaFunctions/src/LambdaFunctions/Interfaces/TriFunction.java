package LambdaFunctions.Interfaces;

@java.lang.FunctionalInterface
public interface TriFunction <T, U, V, R> {

        // argument, argument, argument, result

        R apply(T t, U u, V v);
}
