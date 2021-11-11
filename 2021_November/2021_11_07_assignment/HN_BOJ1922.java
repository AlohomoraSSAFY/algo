package com.baekjoon.problem31;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ1922 {
	static int parents[];
	static int N, M, answer;

	static class Edge implements Comparable<Edge> {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		public int compareTo(Edge e) {
			return Integer.compare(this.cost, e.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		// 1. make
		parents = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			parents[n] = n;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		StringTokenizer st;
		int f, t, c;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			pq.add(new Edge(f, t, c));
		}

		answer = 0;
		int cnt = 0;
		while (true) {
			Edge e = pq.poll();
			if (union(e.from, e.to)) {
				answer += e.cost;
				cnt++;
			}

			if (cnt == N - 1)
				break;
		}

		System.out.println(answer);
	}

	private static boolean union(int from, int to) {
		int pa = find(from);
		int pb = find(to);
		
		if(pa == pb)
			return false;
		
		parents[pa] = pb;
		return true;
	}

	private static int find(int o) {
		if(parents[o] == o)
			return o;
		
		return parents[o] = find(parents[o]);
	}

}
