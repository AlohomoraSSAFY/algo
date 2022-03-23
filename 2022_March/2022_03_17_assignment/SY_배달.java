package study0324;

import java.util.*;

public class SY_배달 {
    
    int N;
    List<Road>[] list;
    int[] distance;
    
    class Road implements Comparable<Road> {
        int to;
        int time;
        
        public Road(int to, int time) {
            this.to = to;
            this.time = time;
        }
        
        @Override
        public int compareTo(Road o) {
            return this.time - o.time;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        this.N = N;
        list = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            list[a].add(new Road(b, c));
            list[b].add(new Road(a, c));
        }
        
        dijkstra();
        
        int answer = 0;
        for (int i = 1; i < N+1; i++) {
            if (distance[i] <= K) answer++;
        }
        return answer;
    }
    
    private void dijkstra() {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.offer(new Road(1, 0));
        boolean[] visited = new boolean[N+1];
        distance[1] = 0;
        
        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            
            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            
            for (int i = 0; i < list[cur.to].size(); i++) {
                Road temp = list[cur.to].get(i);
                if (distance[temp.to] > distance[cur.to] + temp.time) {
                    distance[temp.to] = distance[cur.to] + temp.time;
                    pq.offer(new Road(temp.to, distance[temp.to]));
                }
            }
        }
    }
}
