package com.ssafy.algostudy.implementation;

import java.io.*;
import java.util.*;

public class Main14499 {

    static int N, M, K;
    static int x, y;
    static int[][] map;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static class DICE{
        int up; int down;
        int front; int back;
        int right; int left;

        public DICE(int up, int down, int front, int back, int right, int left) {
            this.up = up;
            this.down = down;
            this.front = front;
            this.back = back;
            this.right = right;
            this.left = left;
        }

        public void move(int dir) {
            int up = this.up; int down = this.down;
            int front = this.front; int back = this.back;
            int right = this.right; int left = this.left;

            if(dir==1) {
                this.up = left;	this.down = right;
                this.front = front; this.back = back;
                this.right = up; this.left = down;
            }else if(dir==2) {
                this.up = right; this.down = left;
                this.front = front; this.back = back;
                this.right = down; this.left = up;
            }else if(dir==3) {
                this.up = front; this.down = back;
                this.front = down; this.back = up;
                this.right = right; this.left = left;
            }else {
                this.up = back; this.down = front;
                this.front = up; this.back = down;
                this.right = right; this.left = left;
            }
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 주사위
        DICE dice = new DICE(0, 0, 0, 0, 0, 0);

        // 지도에 담길 값
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
            }
        }

        // 이동하는 명령
        String[] cmd = br.readLine().split(" ");

        // 이동 시작
        for(int k = 0; k < K; k++) {
            int cur = Integer.parseInt(cmd[k]);
            int ny = y + dy[cur-1];
            int nx = x + dx[cur-1];
            if(ny < 0 || nx < 0 || ny > N-1 || nx > M-1) continue;
            dice.move(cur);
            if(map[ny][nx]==0) {
                map[ny][nx] = dice.down;
            }else {
                dice.down = map[ny][nx];
                map[ny][nx] = 0;
            }

            y = ny;
            x = nx;

            bw.write(dice.up+"\n");

        }


        bw.close();
        br.close();

    }

}
