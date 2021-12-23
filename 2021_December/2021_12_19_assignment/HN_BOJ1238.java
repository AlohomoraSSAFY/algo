package com.baekjoon.problem41;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ1238 {
	static int N, M, X, answer;
	static int parents[];
	static int disTo[], disFrom[];
	static boolean check[];
	static List<int[]> routes[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		routes = new LinkedList[N + 1];
		for (int n = 0; n <= N; n++) {
			routes[n] = new LinkedList<>();
		}

		disTo = new int[N + 1];
		disFrom = new int[N + 1];
		int from, to, cost;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());

			routes[from].add(new int[] { to, cost });
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (i != X) {
				max = Math.max(max, djikstra2(i, X) + djikstra2(X, i));
			}
		}
		System.out.println(max);
	}

	private static int djikstra2(int start, int goal) {
		int distance[] = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		boolean visited[] = new boolean[N + 1];
		distance[start] = 0;
		pq.add(new int[] { start, 0 });

		int idx, min;
		while (true) {
			min = Integer.MAX_VALUE;
			int temp[] = pq.poll();
			if (visited[temp[0]] || distance[temp[0]] < temp[1])
				continue;
			idx = temp[0];
			min = temp[1];
			visited[idx] = true;

			if (idx == goal) {
				return min;
			}

			for (int[] o : routes[idx]) {
				if (!visited[o[0]] && min + o[1] < distance[o[0]]) {
					distance[o[0]] = min + o[1];
					pq.add(new int[] { o[0], distance[o[0]] });
				}
			}

		}
	}

}
