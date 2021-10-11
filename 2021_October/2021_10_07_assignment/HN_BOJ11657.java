package com.baekjoon.problem21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 입력의 최대값을 전부 고려할 경우, 
// 최종 거리의 값이 30,000,000,000으로 Integer의 값을 초과한다.
public class HN_BOJ11657 {
	static int N, M, answer;
	static long d[];

	static class Edge {
		int from, to, time;

		public Edge(int from, int to, int value) {
			this.from = from;
			this.to = to;
			this.time = value;
		}
	}

	static List<Edge> bus = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		d = new long[N + 1];
		Arrays.fill(d, Long.MAX_VALUE);

		int from, to, time;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			bus.add(new Edge(from, to, time));
		}

		boolean validation = bellman(1);

		StringBuilder sb = new StringBuilder();
		
		if (validation) {
			for (int i = 2; i <= N; i++) {
				sb.append(d[i] == Long.MAX_VALUE ? -1 : d[i]).append("\n");
			}
		} else {
			sb.append(-1);
		}
		
		System.out.println(sb);
	}

	private static boolean bellman(int start) {
		boolean validation = true;

		d[start] = 0;

		for (int i = 1; i <= N - 1; i++) {
			for (Edge b : bus) {
				if (d[b.from] != Long.MAX_VALUE && d[b.to] > d[b.from] + b.time) {
					d[b.to] = d[b.from] + b.time;
				}
			}
		}

		for (Edge b : bus) {
			if (d[b.from] != Long.MAX_VALUE && d[b.to] > d[b.from] + b.time) {
				validation = false;
			}
		}

		return validation;
	}

}
