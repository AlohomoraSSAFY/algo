package com.ssafy.algostudy.dp;

import java.io.*;
import java.util.*;

public class Main2565 {
    static int T, N;
    static int[] arr;
    static int[] LIS; // 해당 길이를 증가수열 중 맨 끝을 최소값으로 유지
    static int[] index;
    static int size;

    private static int binarySearch(int value){
        int left = 1;
        int right = size;
        int mid = -1;
        while( left < right ){
//            System.out.println("L "+left+" R "+right);
            mid = (left + right) /2;
            if( LIS[mid] >= value){
                right = mid;
            }else {
                left = mid +1;
            }
        }
        return right;
    }


    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./res/2565.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        LIS = new int[N+1];
        index = new int[N+1];
        size = 0;
        LIS[0] = Integer.MIN_VALUE;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            map.put(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        int k = 1;
        for(Integer e : map.keySet()){
            arr[k++] = map.get(e);
        }

        size = 0;
        for(int i = 1 ; i < N+1 ;i++){
            int tmp = size;
            // 중복값이 없으므로 탐색 실패 : 음수값 => 삽입위치로 환산
            if( LIS[size] < arr[i]){
                LIS[++size] = arr[i];
                index[i] = size;
            }else {
                tmp = binarySearch(arr[i]);
                LIS[tmp] = arr[i];
                index[i] = tmp;
            }
        }

        Stack<Integer> stack = new Stack<>();
        for(int i = N; i > 0; i--){
            if(index[i] == size){
                size--;
                stack.push(arr[i]);
            }
        }

        bw.write((N - stack.size())+"\n");

        bw.close();
        br.close();

    }
}
