package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main10971 {

    static int N;
    static int[][] W;
    static int[][] dp;
    static int MAX;

    public static int tsp(int cur, int visited){ // 0 1
        if(visited == (1<<N)-1){ // 다 다시
            if(W[cur][0]==0) return MAX; // 돌아갈 수 없는 경우
            return W[cur][0];
        }
        // [2][10011]
        if(dp[cur][visited]!= MAX){
            return dp[cur][visited];
        }

        for(int i = 0 ; i < N; i++){
            if(W[cur][i] ==0 || (visited & (1<<i))!=0 ) { // 못가거나 이미 방문했으면 넘어감
                continue;
            }
            int tmp = tsp(i, visited | (1<<i)); // 방문
            dp[cur][visited] = Math.min(dp[cur][visited], W[cur][i]+tmp);
        }

        return dp[cur][visited];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        dp = new int[N][ (1<<N) -1];
        MAX =   1000001 * N;
        for(int i = 0 ; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], MAX);
        }

        bw.write(tsp(0, 1)+"\n");
        br.close();
        bw.close();
    }
}
