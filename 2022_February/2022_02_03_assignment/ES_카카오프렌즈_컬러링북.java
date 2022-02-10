package net.acmicpc.feb.week2;

import java.util.*;

public class ES_카카오프렌즈_컬러링북 {
    
    static int N, M;
    static int[][] P;
    static int numberOfArea = 0;
    static int maxSizeOfOneArea = 0;
    static boolean[][] visited;
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    private void bfs(int sr, int sc) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        visited[sr][sc] = true;
        int sum = 1; int val = P[sr][sc];
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0]; int c = cur[1];
            for(int d = 0 ; d < 4; d++){
                int nr = r + dr[d]; int nc = c + dc[d];
                if(nr < 0 || nc < 0 || nr > M-1 || nc > N-1) continue;
                if(!visited[nr][nc] && P[nr][nc]== val){
                    sum++;
                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});
                }
            }
        }
        
        numberOfArea++;
        maxSizeOfOneArea = Math.max(sum, maxSizeOfOneArea);
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        M = m; // 행
        N = n; // 열
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        
        P = new int[M][N];
        for(int i =0;i < M; i++){
            for(int j = 0; j < N; j++){
                P[i][j] = picture[i][j];
            }
        }
        visited = new boolean[M][N];
        
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j] && P[i][j] !=0){
                    bfs(i, j);
                }
            }
        }
        
        
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}