import java.util.*;

class Solution {
    
    static int[] parent;
    static PriorityQueue<Edge> pq;
    
    class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    
    public static int find(int p) {
        if (parent[p] == p) return p;
        return parent[p] = find(parent[p]);
    }
    
    public static void union(int a, int b) {
        int  pa = find(a);
        int pb = find(b);

        if (pa != pb) parent[pb] = pa;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        pq = new PriorityQueue<Edge>();

        for (int i = 0; i < n; i++){
            parent[i] = i;
        }
            
        for (int i = 0; i < costs.length; i++) {
            pq.offer(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (find(cur.start) == find(cur.end)) continue;
            else {
                union(cur.start, cur.end);
                answer += cur.cost;
            }
        }
        
        return answer;
    }
}