package com.ssafy.algostudy.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17070 {

    static int N;
    static int[][] map;
    static int[] dy = {0, 1, 1};
    static int[] dx = {1, 0, 1};
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());


        StringTokenizer st;
        map = new int[N][N];
        dp = new int[N][N][3];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0] = 1;
        for(int i = 0 ; i < N; i++){
            for(int j = 2; j < N; j++){
                if(map[i][j]==1) continue;

                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];

                if(i==0) continue; // 첫번째 열은 세로 불가
                dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];

                if(map[i][j-1]==0 && map[i-1][j]==0)
                    dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1]+ dp[i-1][j-1][2];
            }
        }

        bw.write((dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2])+"\n");
        br.close();
        bw.close();
    }
}
