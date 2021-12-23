package date1223THU;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int to;
	int cost;

	public Edge(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}

}

public class BOJ1238 {

	static int n, m, x;
	static ArrayList<Edge>[] list;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list[a].add(new Edge(b, c));
		}
		int max = 0;
		for (int i = 1; i <= n; i++) {
			max = Math.max(dijkstra(i, x) + dijkstra(x, i), max);
		}
		System.out.println(max);
	}

	public static int dijkstra(int v, int x) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		boolean check[] = new boolean[n + 1];
		int distance[] = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		pq.add(new Edge(v, 0));
		distance[v] = 0;

		while (!pq.isEmpty()) {

			Edge temp = pq.poll();
			int cur = temp.to;

			if (!check[cur]) {

				check[cur] = true;
				
				for (Edge e : list[cur]) {
					if (!check[e.to]) {
						if (distance[e.to] > distance[cur] + e.cost) {
							distance[e.to] = distance[cur] + e.cost;
							pq.offer(new Edge(e.to, distance[e.to]));
						}
					}
				}
			}
		}
		return distance[x];
	}
}
