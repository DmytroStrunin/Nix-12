package com.nixsolutions.utils;

import com.nixsolutions.model.Vehicle;

import java.math.BigDecimal;
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

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private class Node {
        final private T data;
        private Node leftChild;
        private Node rightChild;

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

    public BigDecimal countLeftBranch() {
        return countTree(root.leftChild);
    }

    public BigDecimal countRightBranch() {
        return countTree(root.rightChild);
    }

    private BigDecimal countTree(Node node) {
        BigDecimal counter;
        if (node == null) {
            return BigDecimal.ZERO;
        } else {
            counter = node.data.getPrice();
            counter = counter.add(countTree(node.leftChild));
            counter = counter.add(countTree(node.rightChild));
            return counter;
        }
    }

    public void printTree() {
        printTree(root, null, false);
    }

    private void printTree(Node root, Trunk prev, boolean isLeft) {
        if (root == null) {
            return;
        }
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
        printTree(root.rightChild, trunk, true);
        if (prev == null) {
            trunk.str = "———";
        } else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        } else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
        showTrunks(trunk);
        System.out.println(" " + root.data);
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
        printTree(root.leftChild, trunk, false);
    }

    private void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }
        showTrunks(p.prev);
        System.out.print(p.str);
    }

    private static class Trunk {
        Trunk prev;
        String str;

        Trunk(Trunk prev, String str) {
            this.prev = prev;
            this.str = str;
        }
    }
}

