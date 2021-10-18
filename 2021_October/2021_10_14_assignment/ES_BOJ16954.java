package com.ssafy.algostudy.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main16954 {

//    static char[][] map = new char[8][8];
    static int[][] pos = new int[8][8];
    static int[] dy = {0, -1, 1, 0, 0, -1, -1, 1, 1}; // 9가지 방향으로 움직이는 것 가능
    static int[] dx = {0, 0, 0, -1, 1, -1, 1, -1, 1};
    static int time = 1;

    public static ArrayList<int[]> check(char[][] map){
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0 ; i < 8; i++){
            for(int j = 0 ; j < 8; j++){
                if(map[i][j]=='.' && pos[i][j] == time){
                    list.add(new int[]{i, j});
                }
            }
        }

        return list;
    }

    public static char[][] moveWall(char[][] map){
        char[][] next = new char[8][8];
        for(int j = 0; j < 8; j++){
            next[0][j] = '.';
        }
        for(int i = 1; i < 8 ; i++){
            for(int j = 0; j < 8; j++){
                next[i][j] = map[i-1][j];
            }
        }
        return next;
    }

    public static boolean moveWook(ArrayList<int[]> list, char[][] map){
        boolean flag = false;
        LOOP:
        for(int[] cur : list){
            int y = cur[0]; int x = cur[1];
            for(int d = 0 ; d < 9; d++){
                int ny = y + dy[d];
                int nx = x + dx[d];
                if(ny < 0 || nx < 0 || ny > 7 || nx > 7) continue;
                if(map[ny][nx]=='.'){
                    if(ny==0 && nx ==7){
                        flag = true;
                        break LOOP;
                    }
                    pos[ny][nx] = time; // 해당 구역 마킹
                }
            }
        }

        return flag;
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[][] map = new char[8][8];
        for(int i = 0 ; i < 8; i++){
            map[i] = br.readLine().toCharArray();
        }

        // 욱제는 처음에 (7,0)에서 시작
        for(int d = 0 ; d < 9; d++){
            int ny = 7 + dy[d];
            int nx = 0 + dx[d];
            if(ny < 0 || nx < 0 || ny > 7 || nx > 7) continue;
            if(map[ny][nx]=='.'){
                pos[ny][nx] = time; // 해당 구역 마킹
            }
        }
        map = moveWall(map); // 벽 움직이고
        ArrayList<int[]> list = check(map);// 갈 수 있는 곳 마킹

        boolean flag = true;
        if(list.size() > 0){ // 1초 후에도 움직일 수 있으면 움직이기
            while (true){
                time++;
                flag = moveWook(list, map);
                map = moveWall(map);
                list = check(map);
                if(list.size()==0){
                    flag = false;
                    break;
                }
                if(flag){
                    break;
                }

            }
        }else{
            flag = false;
        }

        if(flag){
            bw.write("1\n");
        }else {
            bw.write("0\n");
        }

        br.close();
        bw.close();
    }
}
