package bigO;

public class ComplejidadLineal {

    /*
    O(N) -> Complejidad Lineal.
    El tiempo de ejecución del algoritmo aumenta linealmente con respecto al tamaño de la entrada.
     */

    public static void main(String[] args) {
        int [] array = {15, 44, 23, 12}; // N
        reverseArray(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }
    }

    public static void reverseArray (int [] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int reversePosition = array.length - i - 1;
            int tempValue = array[reversePosition];
            array[reversePosition] = array[i];
            array[i] = tempValue;
        }
    }


}