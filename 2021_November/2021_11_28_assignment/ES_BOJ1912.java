package com.ssafy.december.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1912 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = Integer.MIN_VALUE;
        dp = new int[N+1];
        for (int i = 1; i < N+1; i++){
            int data = Integer.parseInt(st.nextToken());
            dp[i] = Math.max(dp[i-1] + data, data);
            max = Math.max(dp[i], max);
        }

        bw.write(max+"\n");
        br.close();
        bw.close();
    }
}
