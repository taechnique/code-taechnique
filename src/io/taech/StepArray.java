package io.taech;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StepArray {
    
    
    /**
     * <b> 10818 - 최소,최대
     * <hr>
     * <p>N개의 정수가 주어진다. 이때, 최솟값과 최댓값을 구하는 프로그램을 작성하시오.</p>
     * <p>입력: 첫째 줄에 정수의 개수 N (1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄에는 N개의 정수를 공백으로 구분해서 주어진다. 모든 정수는 -1,000,000보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.</p>
     * <p>출력: 첫째 줄에 주어진 정수 N개의 최솟값과 최댓값을 공백으로 구분해 출력한다.</p>
     * */
    public static void minimumAndMaximum () throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        int [] array = new int [size];

        for(int i = 0;i < size;i++) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(array);

        System.out.printf("%d %d",array[0], array[size -1]);


    }

    public static void maximum () throws Exception {

    }
}
