package sorting_algorithms.ineffective;

import sorting_algorithms.effective.HeapSort;
import sorting_algorithms.utils.PrintArray;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {2,4,567,-234,-52,234,12,764,-23,34,63};
        PrintArray.printArray(array);
        System.out.println("----------");
        bubbleSort(array);
        PrintArray.printArray(array);
    }

    public static void bubbleSort(int[] numbers) {

        int n = numbers.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (numbers[j - 1] > numbers[j]) {
                    temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }
}
