package collections.queue;

public class QueueExample {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.deQueue();
        queue.deQueue();
        queue.enQueue(1);
        queue.enQueue(2);

        queue.deQueue();
        queue.show();
    }


}
