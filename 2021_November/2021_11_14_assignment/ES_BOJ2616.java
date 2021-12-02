package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2616 {

    static int N, M;
    static int[] sum;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        sum = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N; i++){
            sum[i+1] = sum[i] + Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        dp = new int[4][50001];

        for(int i = 1; i < 4; i++){
            for(int j = M * i; j < N+1; j++){
                dp[i][j] = Math.max( dp[i][j-1], dp[i-1][j-M] + (sum[j] - sum[j - M]) );
            }
        }

        bw.write(dp[3][N]+"\n");
        br.close();
        bw.close();
    }
}
