package com.baekjoon.problem49;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ1865 {
	static int TC, N, M, W;
	static int time[];
	static boolean pass;

	static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	static List<Edge> Elist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			Elist = new LinkedList<>();
			time = new int[N + 1];

			int from, to, cost;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());

				Elist.add(new Edge(from, to, cost));
				Elist.add(new Edge(to, from, cost));
			}
			for (int w = 0; w < W; w++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());

				Elist.add(new Edge(from, to, cost * -1));
			}

			pass = false;
			find(1);

			sb.append(pass ? "YES" : "NO").append("\n");
		}

		System.out.println(sb);
	}

	private static void find(int start) {
		// 단절된 그래프가 있을 수 있으니, 
		// 초기값을 Integer.MAX_VALUE로 놓으면 오버플로우가 발생할 수 있다.
		Arrays.fill(time, 100_000);
		time[start] = 0;

		for (int n = 0; n < N; n++) {
			for (Edge e : Elist) {
				if (time[e.to] > time[e.from] + e.cost) {
					time[e.to] = time[e.from] + e.cost;
				}
			}
		}

		for (Edge e : Elist) {
			if (time[e.to] > time[e.from] + e.cost) {
				pass = true;
				return;
			}
		}
	}

}
