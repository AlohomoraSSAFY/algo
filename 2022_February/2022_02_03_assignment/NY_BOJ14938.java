package date0203;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	int to;
	int dist;
	
	
	public Edge(int to, int dist) {
		super();
		this.to = to;
		this.dist = dist;
	}


	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.dist - o.dist;
	}
	
}
public class BOJ14938 {
	static int n, m,r;
	static int[] item;
	static ArrayList<Edge>[] list;
	static int max;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());

		item = new int[n+1];
		list = new ArrayList[n+1];
		
		for(int i=1;i<=n;i++) {
			list[i]= new ArrayList<>();
		}
		
		for(int i=1;i<=n;i++) {
			item[i]= Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			list[a].add(new Edge(b,l));	
			list[b].add(new Edge(a,l));	
		}
		
		max=0;
		
		for(int i=1;i<=n;i++) {
			max = Math.max(max, dijkstra(i));
		}
		System.out.println(max);
	}
	
	public static int dijkstra(int start) {
		int max = 0;
		
		int dist[] = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		boolean[] visited = new boolean[n+1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(start,0)); //현재노드번호, 수색범위, 총 아이템 개수
		dist[start] =0;
		
		while(!pq.isEmpty()) {
			Edge temp = pq.poll();
			int cur = temp.to;
			
			if(!visited[cur]) {
				visited[cur] = true;
				
				for(Edge e : list[cur]) {
					if(!visited[e.to] && dist[e.to] > dist[cur]+ e.dist) {
						 dist[e.to] = dist[cur]+ e.dist;
						 pq.offer(new Edge(e.to, dist[e.to]));
					}
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			if(dist[i] <= m)
				max +=item[i];
		}
		
		return max;
	}

}
