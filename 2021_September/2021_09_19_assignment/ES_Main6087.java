package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main6087 {

    static int W, H;
    static char[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visited;
    static int[][] dp;
    static ArrayList<int[]> laser = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        StringTokenizer st = new StringTokenizer(br.readLine());
//        W = Integer.parseInt(st.nextToken());
//        H = Integer.parseInt(st.nextToken());

        String str ="";
        W = 7;
        H = 8;
        str = ".......\n" +
                "......C\n" +
                "......*\n" +
                "*****.*\n" +
                "....*..\n" +
                "....*..\n" +
                ".C..*..\n" +
                ".......";

        map = new char[H][W];
        for(int i = 0 ; i < H; i++){
//            map[i] = br.readLine().toCharArray();
            map[i] = str.split("\n")[i].toCharArray();
            for(int j = 0 ; j < W; j++){
                if(map[i][j]=='C'){
                    laser.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[H][W];
        dp = new int[H][W];

        int sy = laser.get(0)[0];
        int sx = laser.get(0)[1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(laser.get(0));
        visited[sy][sx] = true;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int y = cur[0]; int x = cur[1];
            for(int d = 0; d < 4; d++){
                int ny = y + dy[d];
                int nx = x + dx[d];
                while (true){
                    if(ny < 0 || nx < 0 || ny > H-1 || nx > W-1) break;
                    if(map[ny][nx]=='*') break;
                    if(!visited[ny][nx]){
                        visited[ny][nx] = true;
                        dp[ny][nx] = dp[y][x] + 1;
                        queue.offer(new int[]{ny, nx});
                    }
                    ny = ny + dy[d];
                    nx = nx + dx[d];
                }
            }
        }

        for(int r = 0; r < H; r++){
            for(int c = 0 ; c < W; c++){
                System.out.print(dp[r][c]+" ");
            }
            System.out.println();
        }

        int ey = laser.get(1)[0];
        int ex = laser.get(1)[1];
        bw.write((dp[ey][ex]-1)+"\n");
        br.close();
        bw.close();
    }
}
