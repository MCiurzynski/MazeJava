/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeLogic;

import java.util.ArrayList;

/**
 *
 * @author golde
 */
public class Queue<T> {

    private Node head;
    private Node last;

    Queue() {
        head = null;
        last = null;
    }

    public void add(T x) {
        var node = new Node(x);
        if (head == null) {
            head = node;
            last = node;
        }
        else {
            Node tmp;
            tmp = last;
            tmp.next = node;
            last = node;
        }
    }

    public T get() throws EmptyQueueException {
        if (head == null)
            throw new EmptyQueueException();
        Node tmp = head;
        head = head.next;
        return tmp.x;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int size() {
        Node tmp = head;
        int n = 1;
        if (head == null)
            return 0;
        while (tmp.next != null) {
            tmp = tmp.next;
            n++;
        }
        return n;
    }
    
    private class Node {
        Node(T x) {
            this.x = x;
            next = null;
        }
        T x;
        Node next;
    }
}

