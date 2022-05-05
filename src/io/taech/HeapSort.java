package io.taech;

import java.util.Arrays;

public class HeapSort {

    public static void makeHeap(int arr []) {
        // last heap
        int lh = (arr.length / 2) - 1;
        System.out.println("arr = " + Arrays.toString(arr));
        int eh = lh;
        while(lh-- > 0) {
            pushDown(arr, lh, eh);
        }

        System.out.println("arr = " + Arrays.toString(arr));
    }

    public static int findLargest(int arr [], int node) {
        // first child
        int fc = (2 * (node + 1)) - 1;
        int n = arr.length - 1;

        if(fc + 1  <= n) {
            if (arr[fc] <= arr[fc + 1]) {
                return arr[fc + 1] <= arr[node] ? node : fc + 1;
            } else {
                return arr[fc] <= arr[node] ? node : fc;
            }
        }
        if (arr[node] < arr[fc]) {
            return fc;
        } else {
            return node;
        }
    }

    public static void pushDown(int arr [], int node, int eh) {
        do {
            int temp = arr[node];
            int large = findLargest(arr, node);

            if(large == node)
                break;

            arr[node] = arr[large];
            arr[large] = temp;

            node = large;
            System.out.println(drawBinaryTree(arr));
        } while(node <= eh);
    }

    public static String drawBinaryTree(int arr []) {
        StringBuilder builder = new StringBuilder();

        int nol = (int) (Math.log(arr.length) / Math.log(2)) + 1;
        int max = (int) Math.pow(2, nol - 1);
        int printed = 0;
        for(int i = 0; i < nol;i++) {
            int perFloor = (int) Math.pow(2, i);

            int last = printed + perFloor;
            for(int j = printed;(j < arr.length && j < last);j++) {
                builder.append(String.format("(%d)", arr[j]));
            }
            builder.append("\n");
            printed += perFloor;
        }

        return builder.toString();
    }
}
