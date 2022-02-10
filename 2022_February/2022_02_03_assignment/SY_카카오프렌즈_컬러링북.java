package study0210;

import java.util.*;

public class SY_카카오프렌즈_컬러링북 {
    
    boolean[][] visited;
    int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int tempCnt, numberOfArea, maxSizeOfOneArea;
    
    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0 || visited[i][j]) continue;
                tempCnt = 1;
                numberOfArea++;
                bfs(i, j, m, n, picture);
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, tempCnt);
            }
        } 

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    private void bfs(int sx, int sy, int m, int n, int[][] picture) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + d[i][0];
                int ny = y + d[i][1];
                
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny]) continue;
                if (picture[nx][ny] != picture[x][y]) continue;
                
                tempCnt++;
                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
}
