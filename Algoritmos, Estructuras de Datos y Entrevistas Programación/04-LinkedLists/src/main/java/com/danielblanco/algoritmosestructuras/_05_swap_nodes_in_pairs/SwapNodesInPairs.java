package com.danielblanco.algoritmosestructuras._05_swap_nodes_in_pairs;

import com.danielblanco.algoritmosestructuras._00_linkedlist.Node;

import java.util.ArrayList;
import java.util.List;

/*
 * Escribe un algoritmo que intercambie parejas de nodos adyacentes sin
 * modificar el valor interno de los nodos.
 *
 * Ejemplo:
 *  Input: 1->2->4->6->8
 *  Output: 2->1->6->4->8
 */
public class SwapNodesInPairs {

    public Node swapNodesInPairs(Node head) {
        Node aux = head;
        Node current = aux;
        while (current != null){
            if (current.next != null) {
                Node n = aux;
                aux = aux.next;
                aux.next = n;
            }
            current = current.next.next;
        }
        return aux.next;
    }
}
