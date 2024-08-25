package bigO;

public class ComplejidadTemporalLineal {

    /*
    O(b) -> Complejidad Temporal Lineal.
    Se retorna el resultado de A sumado con si mismo B veces.
    Crecera linealmente a medida que aumente B
     */

    public static void main(String[] args) {
        System.out.println(sumarNvecesA(2, 12));
    }

    public static int sumarNvecesA (int a, int b) {
        int result = 0;
        for (int i = 0; i < b; i++){
            result += a;
        }
        return result;
    }

}
