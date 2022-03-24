package net.acmicpc.march.week4;

import java.util.*;

public class ES_배달 {
    static class Edge implements Comparable<Edge> {
        int dest, time;
        public Edge(int dest, int time){
            this.dest = dest; this.time = time;
        }
        
        @Override
        public int compareTo(Edge e){
            return this.time - e.time;
        }
        
        @Override
        public String toString() {
            return "["+dest+", "+ time +"]";
        }
    }
    
    static ArrayList<Edge> con[];
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        con = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            con[i] = new ArrayList<>();
        }
        
        int[][] map = new int[N+1][N+1];
        for(int i = 0; i < road.length; i++){
            int a = road[i][0]; int b = road[i][1]; int c = road[i][2];
            con[a].add(new Edge(b, c));
            con[b].add(new Edge(a, c));
        }        
        
        PriorityQueue<Edge> pq = new PriorityQueue();
        long[] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        dist[1] = 0;
        for(Edge next : con[1]){
            if(dist[next.dest] > next.time ){
                dist[next.dest] = next.time;
                pq.add(next);
            }
        }
        
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            for(Edge next : con[cur.dest]){
                if(dist[next.dest] > dist[cur.dest] + next.time ){
                    dist[next.dest] = dist[cur.dest] + next.time;
                    pq.add(next);
                }
            }
        }
        
        for(int i = 1; i < N+1; i++){
            if(dist[i] <= K){
                answer++;
            }
            // System.out.println(dist[i]);
        }
        return answer;
    }
}