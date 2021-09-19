package com.ssafy.algostudy.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class Main1189 {

    static int R, C, K;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int cnt = 0;

    private static void dfs(int y, int x, int k){

        if( k > K ) return;
        if(y==0 && x == C-1){
            if(k == K) cnt++;
            return;
        }

        for(int d = 0 ; d < 4; d++){
            int nr = y + dr[d];
            int nc = x + dc[d];
            if( nr < 0 || nc < 0 || nr > R-1 || nc > C-1) continue;
            if( map[nr][nc] != 'T' && !visited[nr][nc]){
                visited[nr][nc] = true;
                dfs(nr, nc, k+1);
                visited[nr][nc] = false;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./res/1189.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++){
            map[i] = br.readLine().toCharArray();
        }

        visited[R-1][0] = true;
        dfs(R-1, 0, 1);
        bw.write(cnt+"\n");
        bw.close();
        br.close();
    }
}
