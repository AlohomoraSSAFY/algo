package study0512;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ2211 {
	
	static int N, M;
	static List<Line>[] list;
	static int[] dist;
	static int[] from;
	
	static class Line implements Comparable<Line> {
		int to;
		int time;
		
		public Line(int to, int time) {
			this.to = to;
			this.time = time;
		}
		
		@Override
		public int compareTo(Line o) {
			return this.time - o.time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		from = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			list[A].add(new Line(B, C));
			list[B].add(new Line(A, C));
		}
		
		dijkstra();
		
		System.out.println(N-1);
		for (int i = 2; i < N+1; i++) {
			System.out.println(i + " " + from[i]);
		}
	}
	
	private static void dijkstra() {
		PriorityQueue<Line> pq = new PriorityQueue<>();
		pq.offer(new Line(1, 0));
		dist[1] = 0;
		boolean[] visited = new boolean[N+1];
		
		while (!pq.isEmpty()) {
			Line cur = pq.poll();
			if (visited[cur.to]) continue;
			visited[cur.to] = true;
			
			for (int i = 0; i < list[cur.to].size(); i++) {
				Line temp = list[cur.to].get(i);
				if (dist[temp.to] > dist[cur.to] + temp.time) {
					pq.offer(new Line(temp.to, dist[cur.to] + temp.time));
					dist[temp.to] = dist[cur.to] + temp.time;
					from[temp.to] = cur.to;
				}
			}
		}
	}

}
