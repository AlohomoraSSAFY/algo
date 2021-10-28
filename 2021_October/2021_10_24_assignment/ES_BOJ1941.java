package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 소문난 칠공주
public class Main1941 {

    static int N;
    static char[][] map = new char[5][5];
    static int[] X = new int[7];
    static int[] Y = new int[7];

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int total = 0;

    public static void comb(int cnt, int r, int c, int som, boolean[][] visited){

        if(cnt==7){
            if(som >3){ // 이다솜파가 4명 이상일때
                if(bfs(X[0], Y[0], visited)){
                    total++;
                }
            }
            return;
        }

        if(r == 4 && c > 4){
            return;
        }

        if(c > 4) {
            comb(cnt, r+1, 0, som, visited);
            return;
        }

        if(map[r][c]=='S'){
            visited[r][c] = true;
            X[cnt] = r;
            Y[cnt] = c;
            comb(cnt+1, r, c+1, som+1, visited);
            visited[r][c] = false;
        }else{
            X[cnt] = r;
            Y[cnt] = c;
            visited[r][c] = true;
            comb(cnt+1, r, c+1, som, visited);
            visited[r][c] = false;
        }
        comb(cnt, r, c+1, som, visited); // 아예 선택 안함

    }

    public static boolean bfs(int sr, int sc, boolean[][] checked){
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        visited[sr][sc] = true;
        queue.offer(new int[]{sr, sc});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0]; int c  = cur[1];
            for(int d= 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr <0 || nc <0 || nr > 4 || nc > 4) continue;
                if(!visited[nr][nc] && checked[nr][nc]){
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                    count++;
                }
            }
        }

        if(count==7) return true;
        else return false;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < 5; i++){ // 맴 상태 저장
            map[i] = br.readLine().toCharArray();
        }
        boolean[][] checked = new boolean[5][5];
        comb(0, 0, 0, 0, checked);


        bw.write(total+"\n");
        br.close();
        bw.close();
    }
}
