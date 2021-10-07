package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main3019 {

    static int C, P;
    static int[] arr;
    static int total = 0;

    private static void checkOrigin(int cur){
        int[][][] origin = {
                {{0, 0, 0, 0}, {0, 1, 2, 3}},
                {{0, 1, 0, 1}, {0, 0, 1, 1}},
                {{0, 0, 1, 1}, {0, 1, 1, 2}},
                {{0, 0, -1, -1}, {0, 1, 1, 2}},
                {{0, 0, 1, 0}, {0, 1, 1, 2}},
                {{0, 0, 0, 1}, {0, 1, 2, 2}},
                {{0, 1, 0, 0}, {0, 0, 1, 2}}
        };

        int len = origin[cur][1][3] + 1;
        for(int i = 0; i < C; i++){
            int flag = 0;
            int sy = arr[i] +1;
            int sx = i;
            for(int d = 0; d < 4; d++){
                int nr = sy + origin[cur][0][d];
                int nc = sx + origin[cur][1][d];
                if( nc > C -1) break;
                if(arr[nc] + 1 > nr) {
                    flag = -100;
                    break;
                }
                if( arr[nc] + 1 == nr ){
                    flag++;
                }
            }
            if(len == flag){
//                System.out.println("0도 FLAG "+i);
                total++;
            }
        }
    }

    private static void check90(int cur){
        int[][][] degree90 = {
                {{0, 1, 2, 3},{0, 0, 0, 0}},
                {{0, 1, 0, 1}, {0, 0, 1, 1}},
                {{0, 1, 0, -1}, {0, 0, 1, 1}},
                {{0, 1, 1, 2}, {0, 0, 1, 1}},
                {{0, 1, 2, 1}, {0, 0, 0, 1}},
                {{0, 1, 2, 0}, {0, 0, 0, 1}},
                {{0, 1, 2, 2}, {0, 0, 0, 1}}
        };
        int len = degree90[cur][1][3] +1;
        for(int i = 0; i < C; i++){
            int flag = 0;
            int sy = arr[i] +1;
            int sx = i;
            for(int d = 0; d < 4; d++){
                int nr = sy + degree90[cur][0][d];
                int nc = sx + degree90[cur][1][d];
                if( nc > C -1) break;
                if(arr[nc] + 1 >  nr) {
                    flag = -100;
                    break;
                }
                if( arr[nc]+1 == nr ){
                    flag++;
                }
            }
            if(len == flag){
//                System.out.println("90도 FLAG "+i);
                total++;
            }
        }
    }

    private static void check180(int cur){
        int[][][] degree180 = {
                {{0, 0, 0, 0}, {0, 1, 2, 3}},
                {{0, 1, 0, 1}, {0, 0, 1, 1}},
                {{0, 0, 1, 1}, {0, 1, 1, 2}},
                {{1, 1, 0, 0}, {0, 1, 1, 2}},
                {{0, 0, -1, 0}, {0, 1, 1, 2}},
                {{0, 1, 1, 1}, {0, 0, 1, 2}},
                {{0, 0, 0, -1}, {0, 1, 2, 2}}
        };

        int len = degree180[cur][1][3] +1;
        for(int i = 0; i < C; i++){
            int flag = 0;
            int sy = arr[i] + 1;
            int sx = i;
            for(int d = 0; d < 4; d++){
                int nr = sy + degree180[cur][0][d];
                int nc = sx + degree180[cur][1][d];
                if( nc > C -1) break;
                if(arr[nc] + 1 >  nr) {
                    flag = -100;
                    break;
                }
                if( arr[nc] + 1 == nr ){
                    flag++;
                }
            }
            if(len == flag){

//                System.out.println("180도 FLAG "+i);
                total++;
            }
        }
    }

    private static void check270(int cur){
        int[][][] degree270 = {
                {{0, 1, 2, 3},{0, 0, 0, 0}},
                {{0, 1, 0, 1}, {0, 0, 1, 1}},
                {{0, 1, 0, -1}, {0, 0, 1, 1}},
                {{0, 1, 1, 2}, {0, 0, 1, 1}},
                {{0, 1, 0, -1}, {0, 1, 1, 1}},
                {{0, 0, -1, -2}, {0, 1, 1, 1}},
                {{0, 0, 1, 2}, {0, 1, 1, 1}}
        };
        int len = degree270[cur][1][3] +1;
        for(int i = 0; i < C; i++){
            int flag = 0;
            int sy = arr[i] +1;
            int sx = i;
            for(int d = 0; d < 4; d++){
                int nr = sy + degree270[cur][0][d];
                int nc = sx + degree270[cur][1][d];
                if( nc > C -1) break;
                if(arr[nc] + 1 >  nr) {
                    flag = -100;
                    break;
                }
                if( arr[nc] +1 == nr ){
                    flag++;
                }
            }
            if(len == flag){

//                System.out.println("270도 FLAG "+i);
                total++;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) -1;

        arr = new int[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        switch (P){
            case 0:
                checkOrigin(P);
                check90(P);
                break;
            case 1:
                checkOrigin(P);
                break;
            case 2:
                checkOrigin(P);
                check90(P);
                break;
            case 3:
                checkOrigin(P);
                check90(P);
                break;
            case 4:
                checkOrigin(P);
                check90(P);
                check180(P);
                check270(P);
                break;
            case 5:
                checkOrigin(P);
                check90(P);
                check180(P);
                check270(P);
                break;
            case 6:
                checkOrigin(P);
                check90(P);
                check180(P);
                check270(P);
                break;
        }

        bw.write(total+"\n");
        br.close();
        bw.close();
    }
}
