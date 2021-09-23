package com.ssafy.algostudy.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11048 {

    static int N, M;
    static int[] dy = {+1, 0, +1}; //  dir로 봤을 때 왔던 방향으로 이동 불가
    static int[] dx = {0, +1, +1};

    static int[][] map;
    static int[][] dp;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map 상태 입력 받기
        map = new int[N][M];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] visited = new boolean[N][M];
        // dp table 생성
        dp = new int[N][M];
        dp[0][0] = map[0][0];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        visited[0][0] = true;
        while (!queue.isEmpty()){
            // 다음에 탐색할 대상(3가지 방향) 넣기
            int[] cur = queue.poll();
            for(int d = 0; d < 3; d++){
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];
                if(ny < 0 || nx < 0 || ny > N-1 || nx > M-1) continue;
                if(!visited[ny][nx]){
                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx});
                }
            }

            // 위 r-1 c
            if(cur[0]-1 > -1){
                dp[cur[0]][cur[1]] = Math.max(dp[cur[0]][cur[1]], dp[cur[0]-1][cur[1]]+map[cur[0]][cur[1]]);
            }
            // 옆 r c-1
            if(cur[1]-1 > -1){
                dp[cur[0]][cur[1]] = Math.max(dp[cur[0]][cur[1]], dp[cur[0]][cur[1]-1]+map[cur[0]][cur[1]]);
            }
            // 대각선 r-1 c-1
            if(cur[0]-1 > -1 && cur[1]-1 > -1){
                dp[cur[0]][cur[1]] = Math.max(dp[cur[0]][cur[1]], dp[cur[0]-1][cur[1]-1]+map[cur[0]][cur[1]]);

            }
        }

        bw.write(dp[N-1][M-1]+"\n");
        br.close();
        bw.close();
    }
}
