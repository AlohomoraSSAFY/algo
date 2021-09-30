package com.ssafy.algostudy.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 1학년
public class Main5557 {

    static int N;
    static int[] arr;
    static int ans;
    static long total = 0;
    static long[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N-1; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        ans = Integer.parseInt(st.nextToken());// 나와야 하는 최종 값

        dp = new long[N][21];
        dp[0][arr[0]] = 1;
        for(int i = 1 ; i < N-1; i++){
            for(int v = 0 ; v < 21; v++){
                if(dp[i-1][v] != 0){
                    if( v - arr[i] > -1){
                        dp[i][ v-arr[i] ] += dp[i-1][v];
                    }
                    if(v+arr[i] < 21){
                        dp[i][ v+arr[i] ] += dp[i-1][v];;
                    }
                }
            }
        }

//        System.out.printf("%3s", " ");
//        System.out.printf("%3s", " ");
//        for(int j = 0 ; j < 21; j++){
//            System.out.printf("%3d", j);
//        }
//        System.out.println();
//        for(int i = 0 ; i < N-1; i++){
//            System.out.printf("%3d", i);
//            System.out.printf("%3d", arr[i]);
//            for(int j = 0 ; j < 21; j++){
//                System.out.printf("%3d", dp[i][j]);
//            }
//            System.out.println();
//        }

        bw.write(dp[N-2][ans]+"\n");
        br.close();
        bw.close();
    }
}
