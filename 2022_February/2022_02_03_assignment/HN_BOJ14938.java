package com.baekjoon.problem50;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ14938 {
	static int N, M, R, answer;
	static int item[];
	static int visited[];

	static class Edge implements Comparable<Edge> {
		int to, cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.cost, this.cost);
		}
	}

	static List<Edge> elist[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		item = new int[N + 1];
		elist = new LinkedList[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
			elist[i] = new LinkedList<>();
		}
		int from, to, cost;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			if (cost > M) {
				continue;
			}
			elist[from].add(new Edge(to, cost));
			elist[to].add(new Edge(from, cost));
		}

		answer = 0;
		for (int i = 1; i <= N; i++) {
			visited = new int[N + 1];
			Arrays.fill(visited, -1);
			bfs(i, M);
		}
		System.out.println(answer);
	}

	private static void bfs(int start, int cur) {
		int result = item[start];
		Queue<Edge> q = new LinkedList<>();
		q.add(new Edge(start, cur));
		visited[start] = cur;

		while (!q.isEmpty()) {
			Edge e = q.poll();

			for (Edge edge : elist[e.to]) {
				int temp = e.cost - edge.cost;
				if (visited[edge.to] == -1 && temp >= 0) {
					result += item[edge.to];
					visited[edge.to] = temp;
					q.add(new Edge(edge.to, temp));
				} else if (visited[edge.to] != -1 && visited[edge.to] < temp) {
					visited[edge.to] = temp;
					q.add(new Edge(edge.to, temp));
				}
			}
		}

		answer = Math.max(answer, result);
	}

}
