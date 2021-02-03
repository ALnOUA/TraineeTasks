package sorting_algorithms.ineffective;

import sorting_algorithms.effective.HeapSort;
import sorting_algorithms.utils.PrintArray;

public class ShellSort {
    public static void main(String[] args) {
        int[] array = {2,4,567,-234,-52,234,12,764,-23,34,63};
        PrintArray.printArray(array);
        System.out.println("----------");
        shellSort(array);
        PrintArray.printArray(array);
    }

    public static void shellSort(int[] numbers){
        int j;
        for( int gap = numbers.length / 2; gap > 0; gap /= 2 ){
            for(int i=gap;i<numbers.length;i++){
                int temp = numbers[i];
                for (j = i; j >= gap && numbers[j - gap] > temp; j -= gap)
                {
                    numbers[j] = numbers[j - gap];
                }
                numbers[j] = temp;
            }
        }
    }
}
