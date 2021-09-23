package com.ssafy.algostudy.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main12865 {

    static int N, K;
    static int[][] dp;
    static ArrayList<int[]> product = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
        N = 10; K = 999;
        String str =
                "46 306\n" +
                "60 311\n" +
                "33 724\n" +
                "18 342\n" +
                "57 431\n" +
                "49 288\n" +
                "12 686\n" +
                "89 389\n" +
                "82 889\n" +
                "16 289";

        for(int i = 0 ; i < N; i++){
//            st = new StringTokenizer(br.readLine());
            StringTokenizer st = new StringTokenizer(str.split("\n")[i]);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            product.add(new int[]{a, b});
        }
        int maxValue = 0;
        dp = new int[N+1][K+1];
        for(int i = 1; i < N+1; i++){
            int[] cur = product.get(i-1);
            for(int j = 0; j < K+1; j++){ // 현재 무게 j
                if(cur[0] <= j){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cur[0]] + cur[1]);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

//        for(int i = 1; i < N+1; i++){
//            for(int j = 0; j < K+1; j++){ // 현재 무게 j
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        bw.write(dp[N][K]+"\n");
        br.close();
        bw.close();
    }
}
