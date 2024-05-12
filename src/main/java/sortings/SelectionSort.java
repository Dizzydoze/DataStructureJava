package sortings;

import java.util.Arrays;

/**
 * select the smallest number index and swap with current index
 */
public class SelectionSort {
    public void sort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int indexOfMin = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[indexOfMin]){      // the min number keeps being updated
                    indexOfMin = j;                 // update smallest index
                }
            }
            // swap current index with indexOfMin
            int tmp = arr[i];
            arr[i] = arr[indexOfMin];
            arr[indexOfMin] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{15, 12, 33, 9, 5, 21, 17};
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
