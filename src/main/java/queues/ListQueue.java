package queues;

public class ListQueue implements Queue{

    private Node head;
    private Node tail;


    private class Node{
        private Object element;
        private Node next;

        public Node(Object element){
            this.element = element;
            this.next = null;
        }

        public Node(Object element, Node next){
            this.element = element;
            this.next = next;
        }

        public Object getElement() {
            return element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * add to the tail
     * O(1)
     * @param elem
     */
    @Override
    public void enqueue(Object elem) {
        Node newNode = new Node(elem);
        if (empty()){
            head = newNode;
            tail = head;
        }
        else{
            tail.setNext(newNode);
            tail = tail.getNext();      // move tail to the end of the list
        }
    }

    /**
     * Remove the first element from the queue and return its value
     * O(1)
     * @return the first element in the queue
     */
    @Override
    public Object dequeue() {
        // check if empty first
        if (empty()){
            System.out.println("Queue is empty");
            return null;
        }
        Object dequeuedElem = head.getElement();
        head = head.getNext();
        // Key: reset the tail if needed(head is the only node in the list)
        if (head == null){
            tail = null;
        }
        return dequeuedElem;
    }

    /**
     * O(1)
     * @return true if head is null, else false
     */
    @Override
    public boolean empty() {
        return head == null;
    }
}
