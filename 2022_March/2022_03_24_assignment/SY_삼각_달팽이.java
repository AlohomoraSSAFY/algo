package study0331;

public class SY_삼각_달팽이 {
    
    int[][] d = {{1, 0}, {0, 1}, {-1, -1}};
    
    public int[] solution(int n) {
        int[][] array = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        int target = (1 + n) * n / 2;
        int idx = 1;
        int dir = 0;
        int x = 0;
        int y = 0;
        array[x][y] = idx++;
        visited[x][y] = true;
        while (idx <= target) {
            int nx = x + d[dir][0];
            int ny = y + d[dir][1];
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
                dir = (++dir) % 3;
                nx = x + d[dir][0];
                ny = y + d[dir][1];
            }
            
            array[nx][ny] = idx++;
            visited[nx][ny] = true;
            x = nx;
            y = ny;
        }
        
        int[] answer = new int[target];
        idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = array[i][j];
            }
        }
        return answer;
    }
}
