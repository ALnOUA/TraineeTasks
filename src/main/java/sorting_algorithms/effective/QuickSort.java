package sorting_algorithms.effective;

import sorting_algorithms.utils.PrintArray;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {2,4,567,-234,-52,234,12,764,-23,34,63};
        PrintArray.printArray(array);
        quicksort(array,0,array.length-1);
        System.out.println("------");
        PrintArray.printArray(array);

    }


    public static void quicksort(int[] numbers, int low, int high) {
        if (low < high) {
            int dp = partition(numbers, low, high);
            quicksort(numbers, low, dp-1);
            quicksort(numbers, dp+1, high);
        }
    }

    private static int partition(int[] numbers, int low, int high) {
        int pivot = numbers[low];
        int i = low;
        for (int j = low + 1; j <= high; j++)
            if (numbers[j] < pivot) {
                ++i;
                swap(numbers, i, j);
            }
        swap(numbers, low, i);
        return i;
    }

    private static void swap(int[] list, int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
