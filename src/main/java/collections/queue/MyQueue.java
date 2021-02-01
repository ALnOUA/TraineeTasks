package collections.queue;

import lombok.extern.java.Log;

@Log
public class MyQueue {
    int queue[] = new int[5];
    int size;
    int front;
    int rear;

    public void enQueue(int data) {
        if(!isFull()) {
            queue[rear] = data;
            rear = (rear + 1) % 5;
            size = size + 1;
        }
        else log.info("Queue is full");
    }

    public void show() {
        for (int i = 0; i < size; i++) {
            log.info(queue[(front+i)%5] + " ");
        }
    }
    public int deQueue(){
        int data = queue[front];
        if(!isEmpty()) {
            front = (front + 1) % 5;
            size = size - 1;
        }
        else {
            log.info("Queue is empty");
        }
            return data;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return getSize()==0;
    }

    public boolean isFull(){
        return getSize()==5;
    }

}
