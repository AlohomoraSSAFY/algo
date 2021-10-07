package com.ssafy.algostudy.math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main13164 {

    static int N, K;
    static long[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }


        if(K==1){
            bw.write((arr[N-1]-arr[0])+"\n");
        }else{
            long total = 0;
            Long[] diff = new Long[N-1];
            for(int i = 1 ; i < N; i++){
                diff[i-1] = arr[i] - arr[i-1];
            }
            Arrays.sort( diff, Collections.reverseOrder());
            for(int i = K-1; i < N-1; i++){
                total += diff[i];
            }
            bw.write(total+"\n");
        }

        br.close();
        bw.close();
    }
}
