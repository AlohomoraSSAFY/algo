package com.baekjoon.problem36;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class HN_가장_먼_노드 {
	static int[] distance;
    static int max;
    static List<Integer> nodes[];
    static class Point implements Comparable<Point>{
        int to, weight;
        
        Point(int to, int weight){
           this.to = to;
           this.weight = weight;
        }
        
        public int compareTo(Point p){
            return Integer.compare(this.weight, p.weight);
        }
    }
    static PriorityQueue<Point> pq;
    public int solution(int n, int[][] edge) {
        distance = new int[n+1];
        nodes = new LinkedList[n+1];
        for(int no = 0; no <= n; no++){
            distance[no] = Integer.MAX_VALUE;
            nodes[no] = new LinkedList<>();
        }
        distance[1] = 0;
        
        for(int s1 = 0; s1 < edge.length; s1++){
            nodes[edge[s1][0]].add(edge[s1][1]);
            nodes[edge[s1][1]].add(edge[s1][0]);
        }
        
        find(n);
        
        int answer = 0;
        max = 0;
        for(int no = 2; no <= n; no++){
            if(max < distance[no]){
                answer = 1;
                max = distance[no];
            }else if(max == distance[no]){
                answer++;
            }
        }
        
        return answer;
    }
    
     public static void find(int cnt) {
         max = 0;
         boolean[] visited = new boolean[cnt+1];
         pq = new PriorityQueue<>();
         pq.add(new Point(1, 0));
         
         int idx;
         while(cnt > 0){
             Point p = pq.poll();
             
             if(visited[p.to]) continue;
             
             visited[p.to] = true;
             idx = p.to;
             cnt--;
             
             for(int no = 0; no < nodes[idx].size(); no++){
                 Integer to = nodes[idx].get(no);
                 if(distance[to] > distance[idx] + 1){
                     distance[to] = distance[idx] + 1;
                     pq.add(new Point(to, distance[to]));
                 }
             }
         }// while
     }

}
