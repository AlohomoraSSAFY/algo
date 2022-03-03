package date0303;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
	int from;
	int to;
	double dist;

	public Edge(int from, int to, double dist) {
		super();
		this.from = from;
		this.to = to;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		if (this.dist < o.dist)
			return -1;
		else
			return 1;
	}
}

class UFO {
	int x;
	int y;

	public UFO(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class BOJ1774 {

	static int n;
	static long m;
	static double answer;
	static int[] parent;
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue<>();
		answer = 0;
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}

		UFO[] ufo = new UFO[n + 1];
		int[][] map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			ufo[i] = new UFO(x, y);
		}

		ArrayList<Integer> list[] = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (find(i) != find(j)) {
					double dist = Math.sqrt(Math.pow(ufo[i].x - ufo[j].x, 2) + Math.pow(ufo[i].y - ufo[j].y, 2));
					pq.add(new Edge(i, j, dist));
				}
			}
		}

		///

		int count = 0;
		while (!pq.isEmpty()) {
			Edge temp = pq.poll();
			int a = find(temp.from);
			int b = find(temp.to);
			if (a == b)
				continue;
			union(a, b);
			answer += temp.dist;
			if (count++ == n)
				break;
		}

		System.out.println(String.format("%.2f", answer));
	}

	public static void union(int a, int b) {

		a = find(a);
		b = find(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	public static int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}
}
