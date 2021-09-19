package com.ssafy.algostudy.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main2156 {

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str= "";
        N = 6;
        str = "6\n" +
                "10\n" +
                "13\n" +
                "9\n" +
                "8\n" +
                "1";
        N = 2;
        str = "1\n2";

//        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        int cnt = 0;
        boolean flag = false;
        for(int i = 0 ; i < N; i++){
//            arr[i] = Integer.parseInt(br.readLine());
            arr[i] = Integer.parseInt(str.split("\n")[i]);
        }

        // N-3    O
        // N-2  O X O
        // N-1  O O X
        // N    X O O
        if(N > 2){
            dp[0] = arr[0];
            dp[1] = arr[0] + arr[1];
            dp[2] = Math.max(dp[1], Math.max(arr[0]+arr[2], arr[1]+arr[2]));
            for(int i = 3; i < N; i++){
                dp[i] = Math.max(dp[i-1], Math.max(dp[i-2]+arr[i], dp[i-3] + arr[i-1]+ arr[i]));
            }
        }else{
            if(N==2) dp[0] = arr[0]+arr[1];
            else dp[0] = arr[0];
        }


        Arrays.sort(dp);
        bw.write(dp[N-1]+"\n");
        bw.close();
        br.close();
    }
}
