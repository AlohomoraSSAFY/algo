package study1205;

import java.util.*;

public class SY_가장_먼_노드 {
    
    static final int INF = Integer.MAX_VALUE;
    static List<Integer>[] list;
    static int[] distance;
    
    public int solution(int n, int[][] edge) {
        list = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            list[a].add(b);
            list[b].add(a);
        }
        
        distance = new int[n+1];
        Arrays.fill(distance, INF);
        distance[1] = 0;
        
        bfs();
        
        int max = 0;
        int answer = 0;
        for (int i = 2; i < n+1; i++) {
            if (distance[i] > max) {
                max = distance[i];
                answer = 1;
            } else if (distance[i] == max) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < list[cur].size(); i++) {
                int temp = list[cur].get(i);
                if (distance[temp] != INF) continue;
                distance[temp] = distance[cur] + 1;
                q.offer(temp);
            }
        }
    }
}
