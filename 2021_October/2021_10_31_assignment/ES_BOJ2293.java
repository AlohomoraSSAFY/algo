package com.ssafy.algostudy.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2293 {

    static int N, K;
    static int[] coin;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coin = new int[N+1];
        for(int i = 1 ; i < N+1; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coin); // 정렬
        int[][] dp = new int[N+1][K+1];
        dp[0][0] = 1;
        for(int i = 1; i < N+1; i++){
            dp[i][0] = 1;
            for(int j = 1; j < K+1; j++){ // 1 2 5
                if( j - coin[i] < 0){
                    dp[i][j] = dp[i-1][j];
                }else{
                    dp[i][j] = dp[i][j - coin[i]] + dp[i-1][j];
                }
            }
        }

//        for(int i = 0; i < N+1; i++){
//            for(int j = 0; j < K+1; j++){
//                System.out.printf("%3d", dp[i][j]);
//            }
//            System.out.println();
//        }


        
        bw.write(dp[N][K]+"\n");
        br.close();
        bw.close();
    }
}
