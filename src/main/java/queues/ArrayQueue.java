package queues;

/**
 * KEY: circular array with moving head and tail, using mod.
 */
public class ArrayQueue implements Queue{

    private Object[] data;  // the array that will store the queue
    private int head;
    private int tail;
    private int size;       // max element the queue can hold

    public ArrayQueue(int maxSize){
        this.data = new Object[maxSize];
        head = 0;
        tail = 0;
        size = maxSize;
    }

    /**
     * O(1), add to tail index, update tail.
     * @param elem
     */
    @Override
    public void enqueue(Object elem) {
        // before adding, check if the queue is full, when (tail + 1) meet head in a circular, the queue is full
        if ((tail + 1) % size == head){
            System.out.println("Qeueu is full: Can't add an element");
            return;
        }
        data[tail] = elem;
        tail = (tail + 1) % size;
    }

    /**
     * Dequeues the element from the queue
     * O(1), return the head value and update head.
     * @return element that was dequeued
     */
    @Override
    public Object dequeue() {
        // check if the queue is empty
        if (empty()){
            System.out.println("Queue is empty");
            return null;
        }

        Object retVal = data[head];
        head = (head + 1) % size;
        return retVal;
    }

    @Override
    public boolean empty() {
        return head == tail;
    }
}
