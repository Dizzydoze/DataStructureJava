package stacks;

public class ListStack implements Stack{

    private Node top;

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

        public Object getElement(){
            return element;
        }

        public Node next(){
            return next;
        }

    }

    /**
     * push element to the head of the LinkedList, create new node, set next to the original top, update top as new head
     * O(1)
     * @param elem
     */
    @Override
    public void push(Object elem) {
        top = new Node(elem, top);
    }

    /**
     * O(1)
     * @return  the top element in the LinkedList
     */
    @Override
    public Object pop() {
        if (isEmpty()){
            return null;
        }
        Object poppedElement = top.getElement();
        top = top.next();       // update top
        return poppedElement;
    }

    /**
     * O(1)
     * @return true if LinkedList is null, else false
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }
}
