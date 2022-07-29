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
                    "model=" + data.getModel() +
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

    public BigDecimal countLeftBranch(){
        return countTree(root.leftChild);
    }

    public BigDecimal countRightBranch(){
        return countTree(root.rightChild);
    }

    private BigDecimal countTree(Node node) {
        BigDecimal counter = BigDecimal.ZERO;
        if (root == null) {
            return BigDecimal.ZERO;
        } else {
            counter = counter.add(countTree(root.leftChild));
            counter = counter.add(countTree(root.rightChild));
            return counter;
        }
    }

    public void printTree(){
        printTree1(root, null, false);
    }

    private void printTree1(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }

        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);

        printTree1(root.rightChild, trunk, true);

        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }

        showTrunks(trunk);
        System.out.println(" " + root.data);

        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";

        printTree1(root.leftChild, trunk, false);
    }

    private void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }
        showTrunks(p.prev);
        System.out.print(p.str);
    }
}
class Trunk
{
    Trunk prev;
    String str;

    Trunk(Trunk prev, String str)
    {
        this.prev = prev;
        this.str = str;
    }
};
