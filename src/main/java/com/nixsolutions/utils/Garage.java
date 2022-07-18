package com.nixsolutions.utils;

import com.nixsolutions.model.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Garage} class
 *
 * @author Strunin Dmytro
 * @version 1.0
 */
public class Garage<T extends Vehicle>{
    private int size;
    private Node head;



    private class Node{
        private T data;
        private Node prev;
        private Node next;
        int restylingNumber;
        String date;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        public Node(T data, Node prev, Node next, int restylingNumber) {
            this.data = data;
            this.prev = prev;
            this.next = next;
            this.restylingNumber=restylingNumber;
            this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d.MM.yyyy H:mm"));
//            System.out.println(date);
        }
    }

    public void add(T t){
        if(head == null){
            head = new Node(t, null, null, 69);
        }else{
            head =new Node(t, null, head, 69);
            head.next.prev= head;
        }
        size++;
    }

//    public T findByRetailNumber(){
//
//    }
//
//    public T removeByRetailNumber(){
//
//    }
//
//    public T setByRetailNumber(){
//
//    }

    @Override
    public String toString() {
        Node stNode = head.next;
        StringBuilder stringBuilder = new StringBuilder("Garage{" +
                "size=" + size +
                ", node=" + head);
        while(stNode != null){
            stringBuilder.append(", node=").append(stNode);
            stNode=stNode.next;
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
