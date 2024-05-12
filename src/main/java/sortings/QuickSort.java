package sortings;

import java.util.Arrays;

/**
 * 1. select middle as the pivot
 * 2. swap the pivot with the last element
 * 3. two while loop i and j, find number > pivot on the left, number < pivot on the right, swap
 * 4. swap the last element back to the i index(this is the sorted spot)
 * 5. recursion on [0 i] and [i + 1, len - 1]
 */
public class QuickSort {

    public void sort(int[] array){
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int start, int end){
        if (start < end){
            int pivot = partition(array, start, end);
            quickSort(array, start, pivot);
            quickSort(array, pivot + 1, end);
        }
    }

    private int partition(int[] array, int start, int end){
        int mid = (start + end) / 2;
        int pivot = array[mid];
        swap(array, mid, end);
        int i = start;
        int j = end - 1;

        while (i <= j){
            // move i until bigger num is found
            while (i <= j && array[i] <= pivot){
                i++;
            }
            // move j until smaller num is found
            while (i <= j && array[j] > pivot){
                j--;
            }
            // mismatch pair found, swap
            if (i < j){
                swap(array, i, j);
            }
        }
        // swap pivot back to correct index i
        swap(array, i, end);
        return i;
    }

    private void swap(int[] array, int a, int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{15, 12, 33, 9, 5, 21, 17};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
