package com.danielblanco.algoritmosestructuras.arraysstringshashtables._04_zero_matrix;


import java.util.Arrays;

/*
 * Dada una matriz, escribe un algoritmo para establecer ceros en la fila F y columna C si existe un
 * 0 en la celda F:C
 *
 * Ejemplo:
 *  Input: 2 1 3 0 2
 *         7 4 1 3 8
 *         4 0 1 2 1
 *         9 3 4 1 9
 *
 *  Output: 0 0 0 0 0
 *          7 0 1 0 8
 *          0 0 0 0 0
 *          9 0 4 0 9
 */
public class ZeroMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {2, 1, 3, 0, 2},
                {7, 4, 1, 3, 8},
                {4, 0, 1, 2, 1},
                {9, 3, 4, 1, 9}
        };
        zeroMatrix(matrix);
    }

    public static void zeroMatrix(int[][] matrix) {
        return null;

    }
}
