package io.taech;

import java.util.Arrays;

public class HeapSort {

    public static void makeHeap(int arr[]) {
        makeHeap(arr, false);
    }
    public static void makeHeap(int arr [], boolean needPrint) {
        // last heap
        int lh = arr.length / 2;

        if(needPrint)
            System.out.println("Normal Array = " + Arrays.toString(arr));

        int eh = arr.length;
        while(lh-- > 0) {

            if(needPrint)
                System.out.println("i: " + lh);
            pushDown(arr, lh, eh, needPrint);
        }
        if(needPrint)
            System.out.println("Array as Max Heap = " + Arrays.toString(arr));
    }

    public static int findLargest(int arr [], int node, int eh) {
        // first child
        int fc = (2 * (node + 1)) - 1;

        if(fc + 1  < eh) {
            if (arr[fc] <= arr[fc + 1]) {
                return arr[fc + 1] <= arr[node] ? node : fc + 1;
            } else {
                return arr[fc] <= arr[node] ? node : fc;
            }
        }
        if (fc < eh && arr[node] < arr[fc]) {
            return fc;
        } else {
            return node;
        }
    }

    public static void pushDown(int arr [], int node, int eh, boolean needPrint) {
        do {
            if(needPrint)
                System.out.println("j: " +node);
            int temp = arr[node];
            int large = findLargest(arr, node, eh);
            if(needPrint)
                System.out.println(drawBinaryTree(arr));

            if(large == node)
                break;

            arr[node] = arr[large];
            arr[large] = temp;

            node = large;
        } while(node <= eh);
    }

    public static void sort(int arr [], boolean needPrint) {
        int last = arr.length;
        makeHeap(arr);

        while (--last >= 0) {

            int temp = arr[0];
            arr[0] = arr[last];
            arr[last] = temp;

            if(needPrint)
                System.out.println("last: " + last);

            pushDown(arr, 0 , last, needPrint);
        } ;
    }


    public static String drawBinaryTree(int arr []) {
        StringBuilder builder = new StringBuilder();

        int nol = (int) (Math.log(arr.length) / Math.log(2)) + 1;
        int max = (int) Math.pow(2, nol - 1);

        int printed = 0;
        for(int i = 0; i < nol;i++) {
            int perFloor = (int) Math.pow(2, i);
            int tab = (max - perFloor) / 2 + (max - perFloor) % 2;
            int last = printed + perFloor;

            for (int j = 0; j < tab; j++) {
                builder.append("  ");
            }
            for(int j = printed;(j < arr.length && j < last);j++) {
                    builder.append(String.format("(%d)", arr[j]));
            }
            builder.append("\n");
            printed += perFloor;
        }

        return builder.toString();
    }
}
