package sorting_algorithms.effective;

import sorting_algorithms.utils.PrintArray;

public class HeapSort {

    public static void main(String[] args) {
        int[] array = {2,4,567,-234,-52,234,12,764,-23,34,63};
        PrintArray.printArray(array);
        HeapSort heapSort = new HeapSort();
        System.out.println("----------");
        heapSort.sort(array);
        PrintArray.printArray(array);

    }

    public void sort(int arr[])
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i=n-1; i>=0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i)
    {
        int largest = i;
        int l = 2*i + 1;// here +1
        int r = 2*i + 2;


        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }
}
