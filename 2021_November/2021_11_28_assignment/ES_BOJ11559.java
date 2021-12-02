package com.ssafy.december.week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11559 {

    static int N;
    static int total = 0;
    static char[][] map = new char[12][6];
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static boolean bfs(int sy, int sx, char val){
        boolean flag = false;
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> arr = new LinkedList<>();
        queue.offer(new int[]{sy, sx});
        arr.offer(new int[]{sy, sx});
        visited[sy][sx] = true;

        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int y = cur[0]; int x = cur[1];
            for(int d = 0 ; d < 4; d++){
                int ny = y + dy[d];
                int nx = x + dx[d];
                if(ny < 0 || nx < 0 || ny > 11 || nx > 5) continue;
                if(!visited[ny][nx] && map[ny][nx]==val){
                    cnt++;
                    queue.offer(new int[]{ny, nx});
                    arr.offer(new int[]{ny, nx});
                    visited[ny][nx] = true;
                }
            }
        }

        if(cnt > 3) {
            flag = true;
            for(Iterator<int[]> iter = arr.iterator(); iter.hasNext();){
                int[] cur = iter.next();
                map[cur[0]][cur[1]] = '.';
            }
        }
        return flag;
    }

    public static void move(){
//        printMap();
        for (int j = 0; j < 6; j++){
            Queue<Character> line = new LinkedList<>();
            for(int i = 11; i > -1; i--){
                if(map[i][j]!='.'){
                    line.offer(map[i][j]);
                }
            }
            int idx = 11;
            for(Iterator<Character> iter = line.iterator(); iter.hasNext(); idx--){
                char cur = iter.next();
                map[idx][j] = cur;
            }
            for(int i = idx; i > -1; i--){
                map[i][j] = '.';
            }
        }
//        System.out.println("AFTER");
//        printMap();
    }

    public static void printMap(){
        System.out.println("-------------");
        for(int i = 0; i < 12; i++){
            for(int j = 0; j < 6; j++){
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0 ; i < 12; i++){
            map[i] = br.readLine().toCharArray();
        }

        while (true){
            boolean flag = false;
            visited = new boolean[12][6];
            for(int i = 11; i > -1; i--){
                for(int j = 0; j < 6; j++){
                    if(!visited[i][j] && map[i][j]!='.'){
                       flag |= bfs(i, j, map[i][j]);
                    }
                }
            }
            move();
            if(!flag) break;
            total++;
        }



        bw.write(total+"\n");
        br.close();
        bw.close();
    }
}
