package study0310;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ1504 {
	
	static int N;
	static List<Edge>[] list;
	static int[] dist;
	
	static class Edge implements Comparable<Edge> {
		int to;
		int distance;
		
		public Edge(int to, int distance) {
			this.to = to;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.distance, o.distance);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Edge(b, c));
			list[b].add(new Edge(a, c));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dijkstra(v1);
		int v1v2 = dist[v2];
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dijkstra(1);
		int r1v1 = dist[v1];
		int r1v2 = dist[v2];
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dijkstra(N);
		int r2v1 = dist[v1];
		int r2v2 = dist[v2];
		
		int result = Integer.MAX_VALUE;
		
		if (r1v1 != Integer.MAX_VALUE && r2v2 != Integer.MAX_VALUE) {
			result = Math.min(result, r1v1 + r2v2);
		}
		
		if (r1v2 != Integer.MAX_VALUE && r2v1 != Integer.MAX_VALUE) {
			result = Math.min(result, r1v2 + r2v1);
		}
		
		if (v1v2 != Integer.MAX_VALUE && result != Integer.MAX_VALUE) {
			System.out.println(v1v2 + result);
		} else {
			System.out.println(-1);
		}
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		boolean[] visited = new boolean[N+1];
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (visited[cur.to]) continue;
			visited[cur.to] = true;
			
			for (int i = 0; i < list[cur.to].size(); i++) {
				Edge temp = list[cur.to].get(i);
				if (dist[temp.to] > dist[cur.to] + temp.distance) {
					dist[temp.to] = dist[cur.to] + temp.distance;
					pq.offer(new Edge(temp.to, dist[temp.to]));
				}
			}
		}
	}

}
