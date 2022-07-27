package com.nixsolutions.utils;

import com.nixsolutions.model.Vehicle;

import java.util.Comparator;


/**
 * The {@code BinaryTree} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class BinaryTree<T extends Vehicle> {
    private final Comparator<T> comparator;
    private Node root;
    private int size;

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private class Node {
        private T data;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        public Node(T data) {
            this.data = data;
        }
    }

    public void add(T vehicle) {
        if (root == null) {
            root = new Node(vehicle);
        } else {
            buildNode(root, vehicle);
        }
        size++;
    }

    private void buildNode(Node node, T vehicle) {
        if (comparator.compare(vehicle, node.data) < 0) {
            if (node.leftChild != null) {
                buildNode(node.leftChild, vehicle);
            } else {
                node.leftChild = new Node(vehicle);
            }
        } else if (comparator.compare(vehicle, node.data) > 0) {
            if (node.rightChild != null) {
                buildNode(node.rightChild, vehicle);
            } else {
                node.rightChild = new Node(vehicle);
            }
        }
    }
}
