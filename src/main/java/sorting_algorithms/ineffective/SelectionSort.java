package sorting_algorithms.ineffective;

import sorting_algorithms.utils.PrintArray;

public class SelectionSort {
    public static void main(String[] args) {
        int[] array = {2,4,567,-234,-52,234,12,764,-23,34,63};
        PrintArray.printArray(array);
        System.out.println("----------");
        selectionSort(array,0, array.length-1);
        PrintArray.printArray(array);
    }

    public static void selectionSort(int[] numbers, int low, int high) {
        for (int h = low; h <= high; h++)
            swap(numbers, h, getSmallest(numbers, h, high));
    }

    public static int getSmallest(int[] numbers, int low, int high) {
        int small = low;
        for (int i = low + 1; i <= high; i++)
            if (numbers[i] < numbers[small])
                small = i;
        return small;
    }

    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
