package com.ssafy.algostudy.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main13460 {

    static int N, M;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int gy, gx;
    static char[][] map;
    static int answer = Integer.MAX_VALUE;

    public static int[][] moveUpDown(int[] red, int[] blue, int d){
        int[][] ret = new int[2][2];
        int ry = red[0]; int rx = red[1];
        int by = blue[0]; int bx = blue[1];

        int nry = ry; int nby = by;
        boolean rflag = true; boolean bflag = true;
        while(true){
            RED:
            if(rflag){
                nry = nry + dy[d];
                if(nry <0 || nry > N-1){ // 경계를 넘어가는 경우
                    rflag = false; // stop
                    nry -= dy[d];
                    break RED;
                }
                if(map[nry][rx]=='.'){

                }else if(map[nry][rx]=='#'){
                    rflag = false;
                    nry -= dy[d];
                }else if(map[nry][rx]=='O'){
                    rflag = false;
                }
            }
            BLUE:
            if(bflag){
                nby = nby + dy[d];
                if(nby < 0 || nby > N-1){ // 경계를 넘어가는 경우
                    bflag = false; // stop
                    nby -= dy[d];
                    break BLUE;
                }

                if(map[nby][bx]=='.'){

                }else if(map[nby][bx]=='#'){
                    bflag = false;
                    nby -= dy[d];
                }else if(map[nby][bx]=='O'){
                    bflag = false;
                }
            }


            if(rx == bx){ // 같은 라인인 경우
                if(!rflag){ // 한쪽이 멈춘 경우
                    if(nby==nry && !(nby ==gy && bx == gx)){ // 겹친 경우
                        nby -= (dy[d]);
                        bflag = false;
                    }
                }
                if(!bflag){ // 한쪽이 멈춘 경우
                    if(nry==nby && !(nby ==gy && bx == gx) ){ // 겹친 경우
                        nry -= (dy[d]);
                        rflag = false;
                    }
                }
            }
            if(!rflag && !bflag){ // 둘 다 멈춘 경우
                break;
            }
        }

        ret[0][0] = nry; ret[0][1] = rx;
        ret[1][0] = nby; ret[1][1] = bx;
        return ret;
    }

    public static int[][] moveRightLeft(int[] red, int[] blue, int d){
        int[][] ret = new int[2][2];
        int ry = red[0]; int rx = red[1];
        int by = blue[0]; int bx = blue[1];

        int nrx = rx; int nbx = bx;
        boolean rflag = true; boolean bflag = true;
        while(true){
            RED:
            if(rflag){
                nrx = nrx + dx[d];
                if(nrx < 0 || nrx > M-1){ // 경계를 넘어가는 경우
                    rflag = false; // stop
                    nrx -= dx[d];
                    break RED;
                }
                if(map[ry][nrx]=='.'){

                }else if(map[ry][nrx]=='#'){
                    rflag = false;
                    nrx -= dx[d];
                }else if(map[ry][nrx]=='O'){
                    rflag = false;
                }
            }
            BLUE:
            if(bflag){
                nbx = nbx + dx[d];
                if(nbx < 0 || nbx > M-1){ // 경계를 넘어가는 경우
                    bflag = false; // stop
                    nbx -= dx[d];
                    break BLUE;
                }

                if(map[by][nbx]=='.'){

                }else if(map[by][nbx]=='#'){
                    bflag = false;
                    nbx -= dx[d];
                }else if(map[by][nbx]=='O'){
                    bflag = false;
                }
            }


            if(ry == by){ // 같은 라인인 경우
                if(!rflag){ // 한쪽이 멈춘 경우
                    if(nbx == nrx && !(nrx==gx && ry==gy)){ // 겹친 경우
                        nbx -= (dx[d]);
                        bflag = false;
                    }
                }
                if(!bflag){ // 한쪽이 멈춘 경우
                    if(nrx==nbx && !(nrx==gx && ry==gy)){ // 겹친 경우
                        nrx -= (dx[d]);
                        rflag = false;
                    }
                }
            }
            if(!rflag && !bflag){ // 둘 다 멈춘 경우
                break;
            }
        }

        ret[0][0] = ry; ret[0][1] = nrx;
        ret[1][0] = by; ret[1][1] = nbx;
        return ret;
    }

    public static int check(int[] red, int[] blue, int d){
        int ry = red[0]; int rx = red[1];
        int by = blue[0]; int bx = blue[1];
        int flag = 0;

        if(ry == gy && rx == gx){
            flag = 1;
        }
        if(by == gy && bx == gx){
            flag = 2;
        }
//        System.out.println(d+" "+ry+" "+rx+" "+by+" "+bx+" "+flag);
        return flag;
    }

    private static void comb(int cnt, int[] red, int[] blue, int prev){
        if(cnt==10){
//            System.out.println();
            return;
        }

        for(int i =0; i < 4; i++){
            if(i==prev) continue;
            if(i==0){
                int[][] ret = moveUpDown(red, blue, 0);
                int result = check(ret[0], ret[1], 0);
                if(result==1){ // 성공한 거
                    answer = Math.min(answer, cnt+1);
                }else if(result==2){ // 실패하면 끝
                    continue;
                }else{
                    comb(cnt+1, ret[0], ret[1], 0);
                }
            }else if(i==1){
                int[][] ret = moveUpDown(red, blue, 1);
                int result = check(ret[0], ret[1], 1);
                if(result==1){ // 성공한 거
                    answer = Math.min(answer, cnt+1);
                }else if(result==2){ // 실패하면 끝
                    continue;
                }else{
                    comb(cnt+1, ret[0], ret[1], 1);
                }
            }else if(i==2){
                int[][] ret = moveRightLeft(red, blue, 2);
                int result = check(ret[0], ret[1], 2);
                if(result==1){ // 성공한 거
                    answer = Math.min(answer, cnt+1);
                }else if(result==2){ // 실패하면 끝
                    continue;
                }else{
                    comb(cnt+1, ret[0], ret[1], 2);
                }
            }else if(i==3){
                int[][] ret = moveRightLeft(red, blue, 3);
                int result = check(ret[0], ret[1], 3);
                if(result==1){ // 성공한 거
                    answer = Math.min(answer, cnt+1);
                }else if(result==2){ // 실패하면 끝
                    continue;
                }else{
                    comb(cnt+1, ret[0], ret[1], 3);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int redy = -1, redx=-1;
        int bluey =-1, bluex=-1;
        for(int i = 0 ; i < N; i++){
            map[i] = br.readLine().toCharArray();
            for(int j = 0 ; j < M; j++){
                if(map[i][j]=='R'){
                    redy = i; redx = j;
                }
                if(map[i][j]=='B'){
                    bluey = i; bluex = j;
                }
                if(map[i][j]=='O'){
                    gy = i; gx = j;
                }
            }
        }
//        System.out.println(gy +" "+gx);
        comb(0, new int[]{redy, redx}, new int[]{bluey, bluex}, -1);
        if(answer==Integer.MAX_VALUE){
            bw.write("-1\n");
        }else{
            bw.write(answer+"\n");
        }
        br.close();
        bw.close();
    }
}
