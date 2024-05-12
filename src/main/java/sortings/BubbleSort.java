package sortings;

import java.util.Arrays;

public class BubbleSort {
    public void sort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {            // move bigger num to the end
            for (int j = 0; j < array.length - 1 - i; j++) {    // last i spots are sorted
                if (array[j] > array[j + 1]){
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 12, 72, 25, 33, 29, 17};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
