package com.danielblanco.algoritmosestructuras._02_merge__two_sorted_lists;

import com.danielblanco.algoritmosestructuras._00_linkedlist.Node;

import java.util.LinkedList;

/*
 * Escribe un algoritmo para combinar dos listas enlazadas simples ordenadas.
 * El resultado debe ser una única lista enlazada ordenada. Devuelve su head.
 *
 * Ejemplo:
 *  Input: 1->2->4->6, 2->3->5
 *  Output: 1->2->2->3->4->5->6
 */
public class MergeTwoSortedLists {

    public Node mergeTwoLists(Node list1, Node list2) {
        Node aux = new Node(Integer.MIN_VALUE);
        Node currect = aux;

        while (list1 != null && list2 != null) {
            if (list1.value <= list2.value) {
                currect.next = list1;
                list1 = list1.next;
            } else {
                currect.next = list2;
                list2 = list2.next;
            }
            currect = currect.next; // se avanza una posicion para eliminar el valor inicial de aux q es Integer.MIN_VALUE
        }

        if (list1 == null) {
            agregarRestantes(currect, list2);
        }
        else {
            agregarRestantes(currect, list1);
        }

        return aux.next; // el head de la lista nueva creada
    }

    public void agregarRestantes (Node node, Node list) {
        while (list != null) {
            node.next = list;
            list = list.next;
            node = node.next;
        }
    }
}