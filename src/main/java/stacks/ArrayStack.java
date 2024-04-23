package stacks;

public class ArrayStack implements Stack{

    public static final int DEFAULT_INITIAL_SIZE = 100;
    private int top;       // KEY: top pointer for efficiency, the index right AFTER the element at the TOP of the stack
    private Object[] data;
    private int size;      // current num of element in the stack


    public ArrayStack(){
        this.top = 0;
        this.data = new Object[DEFAULT_INITIAL_SIZE];
    }

    @Override
    public void push(Object elem) {
        if (top == size){
            growStack();        // no more space when top reach size index(out of bound), resize.
        }
        data[top] = elem;
        top++;
    }

    /**
     * Pop the element off the top of the stack.
     * O(1)
     * @return the top element if exists else null.
     */
    @Override
    public Object pop() {
        if (top > 0){               // not empty
            return data[--top];     // decrease first, access later
        }
        return null;
    }

    /**
     * if the stack is empty or not.
     * O(1)
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     *  Allocate twice as much memory for the array that stores the stack elements.
     *  O(N) copy all elements.
     */
    private void growStack(){
        Object[] newData = new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
        this.size = size * 2;
    }
}
