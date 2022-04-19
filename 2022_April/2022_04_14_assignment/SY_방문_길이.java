package study0421;

import java.util.*;

public class SY_방문_길이 {
    public int solution(String dirs) {
        int[][] d = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int[] cur = {5, 5};
        boolean[][][][] visited = new boolean[11][11][11][11];
        Map<Character, Integer> map = new HashMap<>();
        map.put('U', 0);
        map.put('D', 1);
        map.put('R', 2);
        map.put('L', 3);
        
        int answer = 0;
        for (int i = 0; i < dirs.length(); i++) {
            char c = dirs.charAt(i);
            int num = map.get(c);
            int nx = cur[0] + d[num][0];
            int ny = cur[1] + d[num][1];
            
            if (nx < 0 || nx > 10 || ny < 0 || ny > 10) continue;
            if (!visited[cur[0]][cur[1]][nx][ny]) {
                visited[cur[0]][cur[1]][nx][ny] = true;
                visited[nx][ny][cur[0]][cur[1]] = true;
                answer++;
            }
            
            cur[0] = nx;
            cur[1] = ny;
        }
        
        return answer;
    }
}
