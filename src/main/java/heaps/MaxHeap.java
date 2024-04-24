package heaps;

public class MaxHeap {

    private int capacity;   // capacity of the heap array

    private int[] heapArray;

    private int size;   // current number of elements in the heap


    public MaxHeap(int[] array){
        capacity = array.length + 1;    // start from index 1
        heapArray = new int[capacity];
        for (int i = 0; i < array.length; i++) {        // copy all elements from array into heap
            heapArray[i+1] = array[i];
        }
        size = array.length;
        buildFromBottomUp();
    }

    public MaxHeap(){
        capacity = 12;
        heapArray = new int[capacity];
        size = 0;
    }

    private int parent(int pos){
        return pos / 2;
    }

    private int leftChild(int pos){
        return pos * 2;
    }

    private int rightChild(int pos){
        return pos * 2 + 1;
    }

    private boolean isLeaf(int pos){
        return ((pos > size/2) && (pos <= size));
    }

    private void swap(int pos1, int pos2){
        int tmp = heapArray[pos1];
        heapArray[pos1] = heapArray[pos2];
        heapArray[pos2] = tmp;
    }

    private void pushDown(int pos){
        int largestChildIndex;      // always swap with the larger child
        while (!isLeaf(pos)){       // keep going down until it matches heap property or become a leaf node
            largestChildIndex = leftChild(pos);

            if (rightChild(pos) <= size && heapArray[rightChild(pos)] > heapArray[largestChildIndex]){
                largestChildIndex = rightChild(pos);
            }

            if (heapArray[pos] > heapArray[largestChildIndex]){
                return;                        // already matches the heap property
            }
            swap(pos, largestChildIndex);      // pushDown current element, move the larger one up
            pos = largestChildIndex;           // update pos for next round
        }
    }

    private void buildFromBottomUp(){           // heapify
        for (int i = size/2; i > 0; i--) {      // start from the last internal node
            pushDown(i);
        }
    }

    public void insert(int elem){
        if (size + 1 == capacity){              // one more element, out of boundary
            System.out.println("Overflow: not enough capacity");
            return;
        }
        size++;                                 // update size for insertion index
        int pos = size;
        heapArray[pos] = elem;

        while (pos > 1){                        // keep going until it matches heap property or becomes root
            int parentIndex = parent(pos);
            if (heapArray[pos] > heapArray[parentIndex]){
                swap(pos, parentIndex);         // swap with parent if child is bigger
                pos = parentIndex;
            }
            else{
                break;
            }
        }
    }

    public int deleteMax(){
        int maxValue = heapArray[1];
        swap(1, size);      // swap root and last node
        size--;             // move element out of boundary(deleted)
        pushDown(1);
        return maxValue;
    }

    public int[] sort(){
//        int[] sortedArray = new int[size];
//        while (size > 1){       // if size == 2, only 2 elements left, only one more deletion needed
//            sortedArray[size-1] = deleteMax();        // also, delete index 0 might cause problem
//        }
//        return sortedArray;
        while(size > 1){
            deleteMax();
        }
        return heapArray;
    }

    public void print(){
        for (int i = 1; i < size; i++) {
            System.out.print(heapArray[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {5, 9, 1, 12, 34, 4, 2, 8, 6, 0};
        MaxHeap maxHeap = new MaxHeap(arr);
        maxHeap.print();
        // 0 5 1 6 9 4 2 8 12 34
        /*
                       0
                5             1
            6       9      4    2
          8   12  34
        */

         int[] arr2 = {26, 9, 7, 2, 13, 50, 1, 3, 15, 8, 0};
//        int[] arr2 = {5, 9, 1, 12, 34, 4, 2, 8, 6, 0};
        MaxHeap maxHeap1 = new MaxHeap();
        for (int i = 0; i < arr2.length; i++) {
            maxHeap1.insert(arr2[i]);
        }
        maxHeap1.print();

        // heapSort
        int[] sorted = maxHeap.sort();
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println();
        int[] sorted2 = maxHeap1.sort();
        for (int i = 0; i < sorted2.length; i++) {
            System.out.print(sorted2[i] + " ");
        }
    }
}
