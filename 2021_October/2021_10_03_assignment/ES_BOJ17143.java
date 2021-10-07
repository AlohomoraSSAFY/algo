package com.ssafy.algoclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17143 {

    static int R, C, M;
    static int[][] map;
    static int pos = 0; // 낚시 왕의 위치


    private static class SHARK {
        int r, c;
        int speed, dir, size;
        int total = 0;
        boolean status = true;
        public SHARK(int r, int c, int speed, int dir, int size){
            this.r = r; this.c = c;
            this.speed = speed; this.dir = dir; this.size = size;
            this.total = size;
        }
    }

    static ArrayList<SHARK> list = new ArrayList<>(); // 상어 정보를 담고 있음

    // 같은 열에서 가장 가까운 상어 인덱스 정보 반환
    private static int getShark(){
        for(int i = 0 ; i < R; i++){
            if(map[i][pos]!= -1){
                SHARK s = list.get(map[i][pos]);
                s.status = false;
                list.set(map[i][pos], s);
                map[i][pos] = -1;
                return s.size;
            }
        }
        return -1;
    }

    private static void moveShark(){
        for(SHARK s : list){
            if(s.status == true){ // 살아 있는 경우
//                System.out.println("BB "+(s.r+1)+" "+(s.c+1));
                int remain = 0;
                map[s.r][s.c] = -1;
                switch (s.dir){
                    case 1:
                        remain = s.speed % (2*(R-1));
                        if( 2 * s.r >= remain ){
                            if( s.r - remain <= 0 ){
                                remain -= s.r; // 남은거에서 벽까지 빼주고
                                s.r = remain;
                                s.dir = 2;
                            }else {
                                s.r = s.r - remain;
                            }
                        }else{
                            remain -= (2 * s.r) ;
                            if( s.r + remain >= R-1 ){
                                remain -= (R-1 - s.r); // 남은거에서 벽까지 빼주고
                                s.r = R-1 - remain;
                                s.dir = 1;
                            }else {
                                s.r = s.r + remain;
                                s.dir = 2;
                            }
                        }
                        break;
                    case 2:
                        remain = s.speed % (2*(R-1));
                        if( 2 * ((R-1) - s.r) >= remain ){
                            if( s.r + remain >= R-1 ){
                                remain -= (R-1 - s.r); // 남은거에서 벽까지 빼주고
                                s.r = R-1 - remain;
                                s.dir = 1;
                            }else {
                                s.r = s.r + remain;
                            }
                        }else{
                            remain -= (2 * ((R-1) - s.r));
                            if( s.r - remain <= 0 ){
                                remain -= s.r; // 남은거에서 벽까지 빼주고
                                s.r = remain;
                                s.dir = 2;
                            }else {
                                s.r = s.r - remain;
                                s.dir = 1;
                            }
                        }
                        break;
                    case 3:
                        remain = s.speed % (2*(C-1));
                        if( 2 * ((C-1) - s.c) >= remain ){
                            if( s.c + remain >= C-1 ){
                                remain -= (C-1 - s.c); // 남은거에서 벽까지 빼주고
                                s.c = C-1 - remain;
                                s.dir = 4;
                            }else {
                                s.c = s.c + remain;
                                s.dir = 3;
                            }
                        }else{
                            remain -= (2 * ((C-1) - s.c));
                            if( s.c - remain <= 0 ){
                                remain -= s.c; // 남은거에서 벽까지 빼주고
                                s.c = remain;
                                s.dir = 3;
                            }else {
                                s.c = s.c - remain;
                                s.dir = 4;
                            }
                        }

                        break;
                    case 4:
                        remain = s.speed % (2*(C-1));
                        if( 2 * s.c >= remain ){
                            if( s.c - remain <= 0 ){
                                remain -= s.c; // 남은거에서 벽까지 빼주고
                                s.c = remain;
                                s.dir = 3;
                            }else {
                                s.c = s.c - remain;
                            }
                        }else{
                            remain -= (2 * s.c) ;
                            if( s.c + remain >= C-1 ){
                                remain -= (C-1 - s.c); // 남은거에서 벽까지 빼주고
                                s.c = C-1 - remain;
                                s.dir = 4;
                            }else {
                                s.c = s.c + remain;
                                s.dir = 3;
                            }
                        }
                        break;
                }

//                System.out.println("SS "+(s.r+1)+" "+(s.c+1)+" "+s.dir);
            }
        }
    }

    private static void checkSharkSize(){
        for(int i = 0 ; i < list.size(); i++){
            SHARK s = list.get(i);
            if(s.status){
                if(map[s.r][s.c]==-1){ // 상어가 없다는 뜻
                    map[s.r][s.c] = i; //  상어 위치
                }else{ // 상어가 존재하는 경우 -> 크기 비교
                    SHARK comp = list.get(map[s.r][s.c]);
                    if(comp.size < s.size){// 작은 경우 잡아 먹음
                        comp.status = false;
//                        s.total += comp.size;
                        map[s.r][s.c] = i;
                    }else {
                        s.status = false;
//                        comp.total += s.size;
                    }
                    list.set(map[s.r][s.c], comp);
                    list.set(i, s);
                }
            }
        }

//        for(int i = 0 ; i < list.size(); i++){
//            SHARK s = list.get(i);
//            if(s.status){ // 살이 있는 경우 total값을 size로 만들어 주고 map에 그린다
////                s.size = s.total;
//                list.set(i, s);
//                map[s.r][s.c] = i;
//            }
//        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int i = 0 ; i < R; i++){
            Arrays.fill(map[i], -1);
        }
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            list.add(new SHARK(r-1, c-1, s, d, z));
            map[r-1][c-1] = i;
        }

        int total = 0;
        while(pos!= C){
            int ret = getShark();
            if(ret!=-1) total += ret;
//            for(int i = 0 ; i < list.size(); i++){
//                SHARK s = list.get(i);
//                System.out.println(i+"번째 상어 "+ s.status+" "+s.r+" "+s.c);
//            }
            moveShark();
            checkSharkSize();
//            System.out.println("-----------"+pos);
//            for(int i = 0 ; i < R; i++){
//                for(int j = 0 ; j < C; j++){
//                    System.out.printf("%4d", map[i][j]+1);
//                }
//                System.out.println();
//            }
            pos++;
        }

        bw.write(total+"\n");
        br.close();
        bw.close();
    }
}
