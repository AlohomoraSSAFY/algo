package date0310;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	
	int to;
	int dist;
	
	public Edge(int to, int dist) {
		this.to = to;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.dist - o.dist;
	}


	
	
}
public class BOJ1504 {

	static int n,e;
	static int v1,v2;
	static ArrayList<Edge>[] graph;
	
	public static void main(String[] args)throws Exception {
		
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		graph =new ArrayList[n+1];
		
		for(int i=0;i<=n;i++) {
			graph[i] = new ArrayList<Edge>();
		}
		
		for(int i=0;i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b,c));
			graph[b].add(new Edge(a,c));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		long answer = Math.min((long)dijkstra(1, v1)+(long)dijkstra(v1, v2)+(long)dijkstra(v2, n), (long)dijkstra(1, v2)+(long)dijkstra(v2, v1)+(long)dijkstra(v1, n));
		if(answer<=0 || answer ==Integer.MAX_VALUE)
			answer= -1;
		System.out.println(answer);
	}
	public static int dijkstra(int start,int end) {
		int sum =0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[n+1];
		boolean visited[] = new boolean[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] =0;
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge temp = pq.poll();
			int e = temp.to;
			if(!visited[e]) {
				visited[e] = true;
				for(Edge next : graph[e]) {
					if(temp.dist + next.dist < dist[next.to]) {
						dist[next.to] = next.dist + temp.dist;
						pq.add(new Edge(next.to, dist[next.to]));
					}
				}
			}
		}
		if(dist[end] == Integer.MAX_VALUE)
			return 0;
		return dist[end];
	}

}
