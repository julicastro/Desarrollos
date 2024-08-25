package bigO;

public class ComplejidadLogaritmica {

     /*
    O(Log) -> Complejidad Logaritmica.
    Nos va dividiendo el problema entre 2 hasta llegar al resultado.
    En el ejemplo se ve cuantas veces hay q dividir el val por 2 para llegar a 1.
    Equivale a la potencia de 2 para llegar a val.
     */

    public static void main(String[] args) {
        System.out.println(intPowerOfTwo(8));
        // resultado = 3 xq 2^3 = 8;
    }

    public static int intPowerOfTwo (int val) {
        int result = 0;
        while (val > 1){
            result++;
            val /= 2;
        }
        return result;
    }
}
