package study1230;

import java.util.*;

public class SY_섬_연결하기 {

    int N;
    List<Edge>[] list;
    int answer;
    
    public class Edge implements Comparable<Edge> {
        int to;
        int cost;
        
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        N = n;
        list = new ArrayList[n];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int cost = costs[i][2];
            
            list[a].add(new Edge(b, cost));
            list[b].add(new Edge(a, cost));
        }
        
        prim();
        return answer;
    }
    
    public void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 0));
        boolean[] visited = new boolean[N];
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            answer += cur.cost;
            
            for (int i = 0; i < list[cur.to].size(); i++) {
                Edge temp = list[cur.to].get(i);
                if (distance[temp.to] > temp.cost) {
                    distance[temp.to] = temp.cost;
                    pq.offer(new Edge(temp.to, temp.cost));
                }
            }
        }
    }
}
