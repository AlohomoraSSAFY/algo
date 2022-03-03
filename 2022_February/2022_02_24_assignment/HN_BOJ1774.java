package com.baekjoon.problem52;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ1774 {
	static class Point {
		int str, dest;
		double cost;

		public Point(int str, int dest, int x, int y) {
			this.str = str;
			this.dest = dest;
			this.cost = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		}
	}

	static int N, M, parent[], dist[][];
	static double answer;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		dist = new int[N + 1][2];

		int x, y;
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			parent[n] = n;
			dist[n][0] = x;
			dist[n][1] = y;
		}

		int str, dest, cnt = 0;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			str = Integer.parseInt(st.nextToken());
			dest = Integer.parseInt(st.nextToken());
			if (union(str, dest)) {
				cnt++;
			}
		}

		pq = new PriorityQueue<>((p1, p2) -> {
			return Double.compare(p1.cost, p2.cost);
		});

		for (str = 1; str <= N; str++) {
			for (dest = str + 1; dest <= N; dest++) {
				if (find(str) == find(dest))
					continue;

				pq.add(new Point(str, dest, Math.abs(dist[str][0] - dist[dest][0]),
						Math.abs(dist[str][1] - dist[dest][1])));
			}
		}

		answer = 0.0;
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			if (union(p.str, p.dest)) {
				cnt++;
				answer += p.cost;
			}

			if (cnt == N - 1)
				break;
		}
		System.out.println(String.format("%.2f", answer));
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb) {
			return false;
		}

		parent[pb] = pa;
		return true;
	}

	private static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}
}
