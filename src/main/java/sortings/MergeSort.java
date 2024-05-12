package sortings;

import java.util.Arrays;

/**
 * divide and conquer
 * 1. get middle index of current start, end
 * 2. recursion on both left and right
 * 3. while backtracking from recursion, merge based on start, end index
 * 4. remember to offset the temp index while merging two sorted array
 */
public class MergeSort {

    public void sort(int[] arr){
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] array, int start, int end){
        if (start < end){
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    /**
     * merge two sorted list
     */
    private void merge(int[] array, int start, int mid, int end){
        int i = start;  // left part start index
        int j = mid + 1;    // right part start index
        int tmpIndex = 0;
        int[] tmp = new int[end - start + 1];
        while (i <= mid && j <= end){
            if (array[i] <= array[j]){
                tmp[tmpIndex] = array[i];
                i++;
            }
            else{
                tmp[tmpIndex] = array[j];
                j++;
            }
            tmpIndex++;
        }
        while (i <= mid){
            tmp[tmpIndex++] = array[i++];
        }
        while (j <= end){
            tmp[tmpIndex++] = array[j++];
        }

        for (int k = start; k <= end; k++) {
            array[k] = tmp[k - start];      // remember to offset the tmp index
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{15, 12, 33, 9, 5, 21, 17};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
