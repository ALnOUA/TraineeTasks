package sorting_algorithms.ineffective;

import sorting_algorithms.effective.HeapSort;
import sorting_algorithms.utils.PrintArray;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {2,4,567,-234,-52,234,12,764,-23,34,63};
        PrintArray.printArray(array);
        System.out.println("----------");
        insertionSort(array);
        PrintArray.printArray(array);
    }

    public static void insertionSort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int key = elements[i];
            int j = i - 1;
            while (j >= 0 && key < elements[j]) {
                elements[j + 1] = elements[j];
                j--;
            }
            elements[j + 1] = key;
        }
    }
}
