package io.taech;

import java.util.Arrays;

public class InsertionSort {



    public static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;

            while(array[j + 1] < array[j]) {
                array[j + 1] = array[j];
                array[j--] = current;
            }
        }

    }

    public static void sort() {
        int array [] = {1, 4, 7, 3, 2, 5};
        System.out.println("array = " + Arrays.toString(array));

        insertionSort(array);

        System.out.println("array = " + Arrays.toString(array));

    }
}
