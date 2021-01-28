package collections.linkedlist;

import lombok.Getter;
import lombok.extern.java.Log;

@Log
@Getter
public class MyLinkedList {
     private int size=0;
     Node head;
    public int get(int index){
        Node n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n.data;
    }

    public void insert(int data){
        Node node = new Node();
        node.data = data;
        node.next = null;

        if(head==null){
            head=node;
            size++;
        }
        else {
            Node n = head;
            while (n.next!=null){
                n= n.next;
            }
            n.next=node;
            size++;
        }
    }
    public void show(){
        Node node = head;
        while (node.next!=null){
            log.info(String.valueOf(node.data));
            node=node.next;
        }
        log.info(String.valueOf(node.data));
    }
    public void insertAtStart(int data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        node.next = head;
        head = node;
        size++;
    }
    public void insertAt(int index, int data){
        Node node = new Node();
        node.data = data;
        node.next = null;

        if(index==0){
            insertAtStart(data);
            size++;
        }
        else {

            Node n = head;
            for (int i = 0; i < index - 1; i++) {
                n = n.next;
            }
            node.next = n.next;
            n.next = node;
            size++;
        }
    }
    public void deleteAt(int index){
        if(index==0){
            head=head.next;
            size--;
        }
        else {
            Node n = head;
            Node n1 = null;
            for (int i = 0; i < index - 1; i++) {
                n = n.next;
            }
            n1=n.next;
            n.next=n1.next;
            n.next=n1.next;
            size--;
        }

    }
}
