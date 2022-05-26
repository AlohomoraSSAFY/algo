package com.baekjoon.problem59;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ14621 {
	static int N, M;
	static int[] parents;
	static boolean[] womon;

	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		womon = new boolean[N + 1];
		parents = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			if (st.nextToken().equals("W"))
				womon[n] = true;
			parents[n] = n;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.cost, e2.cost));
		int t, f, c;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			f = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			if (womon[t] != womon[f])
				pq.add(new Edge(t, f, c));
		}

		int answer = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();

			if (union(e.from, e.to)) {
				answer += e.cost;
				if (++cnt == N - 1)
					break;
			}
		}

		if (cnt != N - 1)
			answer = -1;

		System.out.println(answer);

	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		parents[pa] = pb;

		return true;
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return find(parents[a]);
	}

}
