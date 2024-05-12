package sortings;

import java.util.Arrays;

/**
 * assume left side sorted, right side not sorted.
 * find the correct insertion spot on the left side of the array.
 */
public class InsertionSort {

    public void sort(int[] array){
        for (int i = 1; i < array.length; i++) {
            // need to find a spot on the left for current number array[i]
            int curr = array[i];
            int j = i - 1;
            // compare to current number, shift, not swap
            while (j >= 0 && array[j] > curr){
                array[j + 1] = array[j];
                j--;
            }
            // insert current number into correct spot
            array[j + 1] = curr;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 12, 72, 25, 33, 29, 17};
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
