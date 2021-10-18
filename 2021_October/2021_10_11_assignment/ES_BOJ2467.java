package com.ssafy.algostudy.twopointer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 용액 - 투포인터
public class Main2467 {

    static int N;
    static long[] arr;
    static long near;
    static int A, B;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new long[N];
        for(int i = 0 ; i < N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        int first = 0; int second = N-1;
        near = arr[first]+arr[second];
        A = first; B = second;
        while (first < second){
            long tmp = arr[first]+arr[second];
            if(Math.abs(tmp) < Math.abs(near)){
                near = tmp;
                A = first;
                B = second;
            }

            if(tmp < 0 ){
                first++;
            }else if(tmp > 0){
                second--;
            }else{
                A = first;
                B = second;
                break;
            }
        }


        bw.write(arr[A]+" "+arr[B]+"\n");

        br.close();
        bw.close();
    }
}
