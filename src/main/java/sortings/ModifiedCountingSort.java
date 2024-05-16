package sortings;

import java.util.Arrays;

/**
 * 1. count array, number of each element
 * 2. modified count array, c[i] = the last index where element i should appear
 * 3. decrement c[i] on the way of sorting
 */
public class ModifiedCountingSort {

    public void sort(int[] arr, int maxValue){
        int[] count = new int[maxValue + 1];

        // counting array
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }

        // modify counting array
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i - 1] + count[i];
        }
        // tmp array
        int[] result = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int currNum = arr[i];
            result[count[currNum]] = currNum;
            count[currNum]--;
        }

        // copy back from temp to original array
        for (int i = 0; i < result.length; i++) {
            arr[i] = result[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12, 2, 6, 43, 27, 18, 31};
        CountingSort countingSort = new CountingSort();
        countingSort.sort(arr, 43);
        System.out.println(Arrays.toString(arr));
    }
}
