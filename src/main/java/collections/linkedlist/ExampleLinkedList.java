package collections.linkedlist;

public class ExampleLinkedList {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.insert(18);
        list.insert(10);
        list.insert(1);
        list.insertAtStart(22);
        list.insertAt(2,6);
        list.deleteAt(2);
        list.show();


    }
}
