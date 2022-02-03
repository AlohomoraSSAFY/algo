package net.acmicpc.feb.week1;

import java.util.*;

public class ES_행렬_테두리_회전하기 {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[][] map;
    static int[] dr = {1, 0, -1, 0}; // 시계 방향 회전
    static int[] dc = {0, 1, 0, -1}; 
    
    public static void rotate(int sr, int sc, int er, int ec){
        int value = map[sr][sc]; int min = value;
        int r = sr; int c = sc;
        int d = 0;
        while(true){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nc > ec || nr > er || nr < sr || nc < sc){ // 행렬 벗어나면
                d++;
                if(d==4){
                    map[r][c+1] = value;
                    min = Math.min(min, map[r][c+1]);
                    break;
                }
                continue;
            }
            map[r][c] = map[nr][nc];
            r = nr;
            c = nc;      
            min = Math.min(min, map[r][c]);
        }
        
        //printMap();
        list.add(min);
    }
    
    public static void printMap(){
         for(int i = 0; i < map.length; i++){
            for(int j =0 ;j < map[0].length; j++){
                System.out.printf("%4d", map[i][j]);
            }
             System.out.println();
        }
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        
        map = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j =0 ;j < columns; j++){
                map[i][j] = (columns * i) + (j + 1);
            }
        } // init map
        
        int r = queries.length;
        for(int i = 0; i < r; i++){ // 실행할 명령문 읽기
            int x1 = queries[i][0]-1;
            int y1 = queries[i][1]-1;
            int x2 = queries[i][2]-1; // X가 행, Y가 열
            int y2 = queries[i][3]-1;
            rotate(x1, y1, x2, y2);
            
        }
        
        
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}