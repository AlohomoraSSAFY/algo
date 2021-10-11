package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

// 성곽
public class Main2234 {

    static int N, M;
    static int[][] map;
    static int[][] number;
    static int[] dy = {0, -1, 0, 1}; // 서 북 동 남
    static int[] dx = {-1, 0, 1, 0};
    static boolean[][] visited;
    static boolean[][] marked;
    static HashMap<Integer, Integer> rooms = new HashMap<>();
    static int IDX = 1;
    static int maxArea = 0;
    static int originMax = 0;

    public static void connectRoom(int sy, int sx){
        int area = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sy, sx});
        visited[sy][sx] = true;
        number[sy][sx] = IDX;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int y = cur[0]; int x = cur[1];
            for(int d = 0; d < 4; d++){// 4방향 탐색
                if( (map[y][x] & (1<<d)) == (1<<d)){ // 해당 방향에 벽 있음
                    continue;
                }
                int ny = y + dy[d];
                int nx = x + dx[d];
                if(ny < 0 || nx < 0 || ny > N-1 || nx > M-1) continue;
                if(!visited[ny][nx]){
                    queue.offer(new int[]{ny, nx});
                    visited[ny][nx] = true;
                    number[ny][nx] = IDX;
                    area++;
                }
            }
        }
        rooms.put(IDX++, area );
        if(originMax < area){
            originMax = area;
        }
    }

    public static void bfs(int sy, int sx){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sy, sx});
        marked[sy][sx] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int y = cur[0]; int x = cur[1];
            int value = number[y][x];
            int area = rooms.get(value);
            for(int d = 0; d < 4; d++){ // 4방향 탐색
                int ny = y + dy[d];
                int nx = x + dx[d];
                if(ny < 0 || nx < 0 || ny > N-1 || nx > M-1) continue;
                if(!marked[ny][nx]){
                    if(number[ny][nx] != value){ // 경계가 다른 방이면 면적 확인
                        int tmp = rooms.get(number[ny][nx]);
                        if( area + tmp > maxArea ){
                            maxArea = area + tmp;
                        }
                    }
                    queue.offer(new int[]{ny, nx});
                    marked[ny][nx] = true;
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

        // 방 입력
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방 나누기
        number = new int[N][M];
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M; j++){
               if(!visited[i][j]){
                   connectRoom(i, j);
               }
            }
        }

        // 인접 방 구하기
        marked = new boolean[N][M];
        bfs(0, 0);

        bw.write((IDX-1)+"\n");
        bw.write(originMax+"\n");
        bw.write(maxArea+"\n");
        br.close();
        bw.close();
    }
}
