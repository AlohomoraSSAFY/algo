package com.ssafy.algostudy.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main9465 {

    static int T, N;
    static int[][][] dp;
    static int[][] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        for(int cs = 1; cs < T+1; cs++){
            N = Integer.parseInt(br.readLine());
            arr = new int[2][N];
            dp = new int[2][N][2];
            for(int i = 0 ; i < 2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j < N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken()); // 스티커 값 넣어주기
                }
            }

            dp[0][0][0] = 0;
            dp[0][0][1] = arr[0][0];
            dp[1][0][0] = 0;
            dp[1][0][1] = arr[1][0];
            for(int i = 1; i < N; i++){
                int one = Math.max(dp[0][i-1][0], dp[0][i-1][1]); // i-1번째 0행 최대
                int two = Math.max(dp[1][i-1][0], dp[1][i-1][1]); // i-1번째 1행 최대
                dp[0][i][0] = Math.max(one, two); // i번째 0행 채택 안할때
                dp[1][i][0] = Math.max(one, two); // i번째 1행 채택 안할때


                dp[0][i][1] = two + arr[0][i]; // i번째 0행 채택 할때
                dp[1][i][1] = one + arr[1][i]; // i번째 1행 채택 할때

            }
            int first = Math.max(dp[0][N-1][0], dp[0][N-1][1]);
            int second = Math.max(dp[1][N-1][0], dp[1][N-1][1]);

//            System.out.println("----------");
//            for(int i = 0 ; i < 2; i++){
//                for(int j = 0 ; j < N; j++) {
//                    System.out.printf("%6d", dp[i][j][0]);
//                }
//                System.out.println();
//                for(int j = 0 ; j < N; j++) {
//                    System.out.printf("%6d", dp[i][j][1]);
//                }
//                System.out.println();
//                System.out.println("+++++++++++");
//            }
//
//            System.out.println("----------");

            bw.write(Math.max(first, second)+"\n");


        }

        br.close();
        bw.close();
    }
}
