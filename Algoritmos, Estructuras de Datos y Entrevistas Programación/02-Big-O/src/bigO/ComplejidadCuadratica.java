package bigO;

public class ComplejidadCuadratica {

     /*
    O(N^2) -> Complejidad Cuadratica.
    El tiempo de ejecución crece proporcionalmente al cuadrado del tamaño de la entrada.
    Esto suele suceder en algoritmos que utilizan dos bucles anidados.
    A pesar de no hacer N ^ 2 iteraciones sobre el array, siendo N el tamaño del array.
    (N - 1) + (N - 2) + (N - 3) + 2 + 1 = N * (N - 1) / 2 = O(N^2)
    Iteración: El código recorre todos los pares no ordenados en el array, evitando imprimir pares
    duplicados como (a, b) y (b, a). Esto se logra al iniciar el segundo bucle desde i + 1.
    Complejidad: La complejidad de este código es O(N^2), ya que por cada elemento en el array,
    el bucle interno recorre los elementos restantes.
     */

    public static void main(String[] args) {
        int [] array1 = {15, 44, 23, 12}; // N
        int [] array2 = {15, 44, 23, 12}; // N
        imprimirParesNoDuplicados(array1, array2);
    }

    public static void imprimirParesNoDuplicados (int [] array1, int [] array2) {
        for (int i = 0; i < array1.length; i++) {
            for (int j = i + 1; j < array2.length; j++) {
                System.out.println(array1[i] + "." + array2[j]);
            }
        }
    }




}
