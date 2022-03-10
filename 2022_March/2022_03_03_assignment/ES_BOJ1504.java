package net.acmicpc.march.week2;

import java.io.*;
import java.util.*;


public class BOJ1504 {
	
	static int N, E;
	static List<Point> list[];
	static final int INF = 200_000_000;
	
	static class Point implements Comparable<Point>{
	    int end;
	    int weight;
	
	    public Point(int end, int weight){this.end = end; this.weight = weight;}
	
	    @Override
	    public int compareTo(Point o) {
	        return weight - o.weight;
	    }
	}
	
	private static int dijkstra(int start, int end){
		int[] dist =  new int[N + 1];
		boolean[] check = new boolean[N + 1];
        
		Arrays.fill(dist, INF);
		
		PriorityQueue<Point> queue = new PriorityQueue<>();
		queue.add(new Point(start, 0));
		dist[start] = 0;
		
		while (!queue.isEmpty()){
		    Point cur = queue.poll();
		    int now = cur.end;
		    int w = cur.weight;
		
		    if(check[now] == true) continue;
		    
		    check[now] = true;
		
		    for(int i = 0; i < list[now].size(); i++){
		        int next = list[now].get(i).end;
		        int nw = list[now].get(i).weight;
		        
		        if(check[next] == false && dist[next] > w + nw){
		            dist[next] = w + nw;
		            queue.add(new Point(next, dist[next]));
		        }
		    }
		}
		
		return dist[end];
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for(int i = 0; i < N+1; i++) {
        	list[i] = new ArrayList<>();        	
        }

        

        // 간선 정보 초기화
        for(int i = 0 ; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            list[start].add(new Point(end, weight));
            list[end].add(new Point(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        
        int answer = 0;
        
        int a = dijkstra(1, v1);
        a += dijkstra(v1, v2);
        a += dijkstra(v2, N);
        
        int b = dijkstra(1, v2);
        b += dijkstra(v2, v1);
        b += dijkstra(v1, N);
        
        answer = Math.min(a, b);
        answer  = answer >= INF ? -1 : answer;
        bw.write(answer + "\n");
		
		bw.close();
		br.close();	
		
	}

}
