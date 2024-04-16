package heaps;

public class MinHeap{

    private int[] heapArray;   // the array to store the heap

    private int capacity;      // capacity of the heapArray

    private int size;          // current number of elements in the heap

    /**
     * constructor for minHeap
     */
    public MinHeap(int[] array){
        capacity = array.length + 1;                // first index is not used, heap start from 1
        heapArray = new int[capacity];
        for (int i = 0; i < array.length; i++) {    // copy from array to heapArray
            heapArray[i+1] = array[i];
        }
        size = array.length;    // KEY: maintain size for node index calculation
        heapArray[0] = Integer.MIN_VALUE;
        buildFromTheBottomUp();
    }

    public MinHeap(){
        capacity = 12;
        heapArray = new int[capacity];
        size = 0;
        heapArray[0] = Integer.MIN_VALUE;
    }

    /**
     * Return the index of the left child of the element at index position
     * @param position the index of the element in the heapArray
     * @return the index of the left child
     */
    private int leftChild(int position){
        return position * 2;
    }

    /**
     * Return the index of the right child of the element at index position
     * @param position the index of the element in the heapArray
     * @return the index of the right child
     */
    private int rightChild(int position){
        return position * 2 + 1;
    }

    /**
     * Return the index of the parent
     * @param position the index of the element in the heapArray
     * @return the index of the parent
     */
    private int parent(int position){
        return position / 2;
    }

    /**
     * Returns true if the node in a given position is a leaf
     * @param position the index of the element in the heapArray
     * @return true if the node is a leaf, false otherwise
     */
    private boolean isLeaf(int position){
        // the left child should be pos * 2, which will be out of boundary
        return ((position > size / 2) && position <= size);
    }

    /** Swap given elements: one at index pos1, another at index pos2
     *
     * @param pos1 the index of the first element in the heap
     * @param pos2 the index of the second element in the heap
     */
    private void swap(int pos1, int pos2){
        int tmp = heapArray[pos1];
        heapArray[pos1] = heapArray[pos2];
        heapArray[pos2] = tmp;
    }

    /** Print the array that stores the heap */
    public void print() {
        int i;
        for (i = 1; i <= size; i++)
            System.out.print(heapArray[i] + " ");
        System.out.println();
    }

    /** Delete minimum element (it is at the top of the minHeap)
     *
     * @return the smallest element in the heap
     */
    public int deleteMin(){
        swap(size, 1);      // heap deletion: swap last element and root, then pushDown root
        size--;             // remove the deleted element out of bound
        if (size != 0){
            pushDown(1);
        }
        return heapArray[size + 1];
    }

    /**
     * Insert new element into heapArray, be careful of the capacity
     * @param element the new element to be inserted
     */
    public void insert(int element){
        if (size + 1 == capacity){      // KEY: if adding one more item will reach capacity, stop
            System.out.println("Overflowed: Could not insert key");
            return;
        }

        // complete binary tree, always add to the last index(size)
        size++;
        int pos = size;
        heapArray[pos] = element;

        // move up until it matches the heap property or reaches root
        while (parent(pos) >= 1){
            int parentIndex = parent(pos);
            if (heapArray[pos] < heapArray[parentIndex]){
                swap(pos, parentIndex);
                pos = parentIndex;      // update current pos to parent
            }
            else{
                break;                  // satisfied heap property, return
            }
        }
    }

    /**
     * Push the value down the heap if it does not satisfy the heap property
     * @param pos the index of the element in the heapArray
     */
    private void pushDown(int pos){
        // keep pushing down the internal until it satisfied heap property or reached the leaf node
        int smallestChildIndex;
        while (!isLeaf(pos)){
            // find the smaller child to swap
            smallestChildIndex = leftChild(pos);
            if (smallestChildIndex + 1 <= size && heapArray[smallestChildIndex+1] < heapArray[smallestChildIndex]){
                smallestChildIndex++;
            }
            // already satisfied the heap property, break
            if (heapArray[pos] < heapArray[smallestChildIndex]){
                return;
            }
            swap(pos, smallestChildIndex);
            // update pos to the child and keep going down
            pos = smallestChildIndex;
        }
    }

    /**
     * Fix the heap from the bottom up
     */
    private void buildFromTheBottomUp(){
        // iterate over internal nodes from right(start from last internal node) to left
        for (int i = size/2; i > 0 ; i--) {
            pushDown(i);
        }
    }


    public static void main(String[] args) {
        int[] arr = {5, 9, 1, 12, 34, 4, 2, 8, 6, 0};
        MinHeap minHeap = new MinHeap(arr);
        minHeap.print();
        //0 5 1 6 9 4 2 8 12 34
        /*
                       0
                5             1
            6       9      4    2
          8   12  34
        */

        int[] arr2 = {26, 9, 7, 2, 13, 50, 1, 3, 15, 8, 0};
        MinHeap minHeap1 = new MinHeap();
        for (int i = 0; i < arr2.length; i++) {
            minHeap1.insert(arr2[i]);
        }
        minHeap1.print();
        // 0 1 2 7 3 50 9 26 15 13 8
    }
}