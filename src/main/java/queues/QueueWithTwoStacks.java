package queues;

import stacks.ArrayStack;
import stacks.Stack;


public class QueueWithTwoStacks implements Queue{

    private Stack s1 = new ArrayStack();        // s1 for input
    private Stack s2 = new ArrayStack();        // s2 for output


    /** Adds an element to the queue
     * O(1) or O(N) if resize happens
     * @param elem  Element to add to the queue
     */
    @Override
    public void enqueue(Object elem) {
        s1.push(elem);
    }

    /**
     * Removes the element from the queue
     * O(N), go through the list 2 times.
     * @return null if empty else the element in front of the queue that was removed
     */
    @Override
    public Object dequeue() {
        if (empty()){
            return null;
        }
        // pop all elements from s1 to s2
        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        // the last element in s2 is the top element, return it
        Object dequeuedElem = s2.pop();
        // pop back from s2 to s1
        while (!s2.isEmpty()){
            s1.push(s2.pop());
        }
        return dequeuedElem;
    }

    /**
     * Check if the queue is empty
     * O(1)
     * @return True if the queue is empty, and false otherwise.
     */
    @Override
    public boolean empty() {
        return false;
    }
}
