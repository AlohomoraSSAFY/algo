package study1209;

import java.util.*;

public class SY_네트워크 {
	static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            
            answer++;
            bfs(i, n, computers);
        }
        
        return answer;
    }
    
    public static void bfs(int c, int n, int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(c);
        visited[c] = true;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < n; i++) {
                if (computers[cur][i] == 0 || visited[i]) continue;
                q.offer(i);
                visited[i] = true;
            }
        }
    }
}
