package net.acmicpc.august.week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {

   static int R, C;
   static char[][] map;
   static int[][] fire;
   static int jr, jc;
   static List<int[]> fires;
   static int[] dr = {-1, 1, 0, 0};
   static int[] dc = {0, 0, -1, 1};
   
   private static void printMap(int[][] tmp) {
      System.out.println("==========");
      for(int i = 0; i < R; i++) {
         for(int j = 0; j < C; j++) {
            System.out.printf("%3d", tmp[i][j]);
         }
         System.out.println();
      }
   }
   
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      
      map = new char[R][C];
      fire = new int[R][C];
      fires = new ArrayList<>();
      
      for(int i = 0; i < R; i++) {
         map[i] = br.readLine().toCharArray();
         for(int j = 0; j < C; j++) {
            if(map[i][j] == '#') { // 벽
               fire[i][j] = -2;
            } else if(map[i][j] == '.') {
               fire[i][j] = -1;
            } else if(map[i][j] == 'J') { // 지훈
               jr = i; jc = j;
               fire[i][j] = -1;
            } else { // 불인 경우
               fires.add(new int[] {i, j});
            }
         }
      }
      
      // 불이 난 곳 time
      Queue<int[]> q = new LinkedList<int[]>();
      boolean[][] visited = new boolean[R][C];
      for(int[] f : fires) {
    	  q.add(new int[] {f[0], f[1]});
          visited[f[0]][f[1]] = true;
      }
      
      int level = 1;
      while(!q.isEmpty()) {
         int size = q.size();
         for(int l = 0; l < size; l++) {
            int[] cur = q.poll();
            int r = cur[0]; int c= cur[1];
            for(int d = 0; d < 4; d++) {
               int nr = r + dr[d];
               int nc = c + dc[d];
               if(nr < 0 || nc < 0 || nr > R-1 || nc > C-1) continue;
               if(!visited[nr][nc]) {
                  visited[nr][nc] = true;
                  if(fire[nr][nc] == -1) { // 방문 가능
                     fire[nr][nc] = level;
                     q.add(new int[] {nr, nc});
                  } 
               }
               
            }
         }
         level++;
      }
      
//      printMap(fire);
      
      // 지훈이 이동
      Queue<int[]> queue = new LinkedList<int[]>();
      queue.add(new int[] {jr, jc});
      visited = new boolean[R][C];
      visited[jr][jc] = true;
      int time = 1;
      boolean check = false;
      LOOP:
      while(!queue.isEmpty()) {
         int size = queue.size();
         for(int l = 0; l < size; l++) {
            int[] cur = queue.poll();
            int r = cur[0]; int c= cur[1];
            
            if(r==0 || c == 0 || r == R-1 || c == C-1) {
            	check = true;
            	break LOOP;
            }
            
            for(int d = 0; d < 4; d++) {
               int nr = r + dr[d];
               int nc = c + dc[d];
               if(nr < 0 || nc < 0 || nr > R-1 || nc > C-1) continue;
               if(!visited[nr][nc]) {
                  visited[nr][nc] = true;
                  boolean flag = false;
                  if(fire[nr][nc] == -1) { // 불이 안 번진 경우
                     flag = true;
                  }
                  if(time < fire[nr][nc]) {
                     flag = true;
                  }
                  
                  if(flag) {
//                     System.out.println("flag");
                     queue.add(new int[] {nr, nc});
                  }
                  
               }
               
            }
         }
         time++;
      }
      
      
      
      bw.write((check ? time : "IMPOSSIBLE")+"\n");
      
      bw.close();
      br.close();
   }

}