package com.ssafy.december.week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261 {

    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer = 0;

    private static class POS implements Comparable<POS>{
        int r, c, wall;

        @Override
        public int compareTo(POS o) {
            return this.wall - o.wall;
        }

        public POS(int r, int c, int wall) {
            this.r = r;
            this.c = c;
            this.wall = wall;
        }

        @Override
        public String toString() {
            return "POS{" +
                    "r=" + r +
                    ", c=" + c +
                    ", wall=" + wall +
                    '}';
        }
    }

    public static void bfs(){
        POS start = new POS(0, 0, 0);
        PriorityQueue<POS> pq = new PriorityQueue<>();
        pq.add(start);
//        System.out.println("START");
//        boolean[][] visited = new boolean[N][M];
//        visited[0][0] = true;
        int[][] count = new int[N][M];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
                count[i][j] = Integer.MAX_VALUE;
            }
        }
        count[0][0] = 0;
        while (!pq.isEmpty()){
            POS cur = pq.poll();
//            System.out.println(cur);
            int r = cur.r; int c = cur.c;
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr < 0 || nc < 0 || nr > N -1 || nc > M-1) continue;
                if(nr==N-1 && nc == M-1) {
                    answer = cur.wall;
                    return;
                }

                if(map[nr][nc]==1){
                    if(count[nr][nc] > cur.wall + 1){
                        pq.add(new POS(nr, nc, cur.wall+1));
                        count[nr][nc] = cur.wall +1;
                    }
                }else{
                    if(count[nr][nc] > cur.wall){
                        pq.add(new POS(nr, nc, cur.wall));
                        count[nr][nc] = cur.wall;
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
//        System.out.println("123");
        for(int i = 0 ; i < N; i++){
            String[] tmp = br.readLine().split("");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(tmp[j]);
            }
        }
//        System.out.println("ssss");
        bfs();
        bw.write(answer+"\n");

        br.close();
        bw.close();
    }
}
