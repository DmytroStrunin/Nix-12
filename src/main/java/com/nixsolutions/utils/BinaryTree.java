package com.nixsolutions.utils;

import com.nixsolutions.model.Vehicle;


/**
 * The {@code BinaryTree} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class BinaryTree<T extends Vehicle> {
    private Node head;
    private int size;

    private class Node {
        private T data;
        private Node small;
        private Node large;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        public Node(T data, Node small, Node large) {
            this.data = data;
            this.small = small;
            this.large = large;
        }
    }

    public void add(T vehicle) {
        if (head == null) {
            head = new Node(vehicle, null, null);
        } else {
            head = new Node(vehicle, null, head);
            head.large.small = head;
        }
        size++;
    }
}
