package study0224;

import java.util.*;

public class SY_거리두기_확인하기 {
    
    static char[][] array;
    static boolean check;
    static int[] answer;
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    
    public int[] solution(String[][] places) {
        answer = new int[5];
        for (int t = 0; t < 5; t++) {
            array = new char[5][5];
            check = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    array[i][j] = places[t][i].charAt(j);
                }
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (array[i][j] == 'P') {
                        bfs(i, j);
                    }
                }
            }
            
            if (check) {
                answer[t] = 0;
            } else {
                answer[t] = 1;
            }
        }
        
        return answer;
    }
    
    private static void bfs(int cx, int cy) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{cx, cy, 0});
        boolean[][] visited = new boolean[5][5];
        visited[cx][cy] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + d[i][0];
                int ny = cur[1] + d[i][1];
                
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny]) continue;
                if (array[nx][ny] == 'X') continue;
                if (array[nx][ny] == 'P') {
                    check = true;
                    break;
                }
                if (cur[2] + 1 <= 1 && array[nx][ny] == 'O') {
                    q.offer(new int[]{nx, ny, cur[2] + 1});
                    visited[nx][ny] = true;
                }
            }
            
            if (check) break;
        }
    }
}
