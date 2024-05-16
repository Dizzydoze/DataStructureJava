package sortings;

import java.util.Arrays;

public class CountingSort {


    public void sort(int[] arr, int maxValue){
        int[] countList = new int[maxValue + 1];
        for (int i = 0; i < arr.length; i++) {
            countList[arr[i]]++;
        }

        int idx = 0;
        for (int num = 0; num < countList.length; num++) {
            int cnt = countList[num];
            while (cnt > 0){
                arr[idx++] = num;
                cnt--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12, 2, 6, 43, 27, 18, 31};
        CountingSort countingSort = new CountingSort();
        countingSort.sort(arr, 43);
        System.out.println(Arrays.toString(arr));
    }
}
