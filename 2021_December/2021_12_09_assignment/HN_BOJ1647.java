package com.baekjoon.problem38;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ1647 {
	static int N, M, answer;
	static int parants[];

	static class Node implements Comparable<Node> {
		int to, from, value;

		public Node(int to, int from, int value) {
			this.to = to;
			this.from = from;
			this.value = value;
		}

		public int compareTo(Node n) {
			return Integer.compare(this.value, n.value);
		}
	}

	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		make();

		int t, f, v;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			f = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			pq.add(new Node(t, f, v));
		}

		int cnt = 0;
		answer = 0;
		while (true) {
			Node n = pq.poll();
			if (union(n.from, n.to)) {
				answer += n.value;
				if (++cnt == N - 1) {
					answer -= n.value;
					break;
				}
			}
		}
		
		System.out.println(answer);
	}

	private static boolean union(int from, int to) {
		int pa = find(from);
		int pb = find(to);

		if (pa == pb) {
			return false;
		}

		parants[pa] = pb;
		return true;
	}

	private static int find(int from) {
		if (parants[from] == from) {
			return from;
		}
		return parants[from] = find(parants[from]);
	}

	private static void make() {
		parants = new int[N + 1];

		for (int n = 1; n <= N; n++) {
			parants[n] = n;
		}
	}

}
