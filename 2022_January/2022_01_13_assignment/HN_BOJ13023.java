package com.baekjoon.problem46;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ13023 {
	static List<Integer> graph[];
	static int N, M;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new LinkedList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		visited = new boolean[N];
		boolean find = false;
		for (int i = 0; i < N; i++) {
			find = false;
			if ((find = check(i, 0))) {
				break;
			}
		}

		int answer = find ? 1 : 0;
		System.out.println(answer);
	}

	private static boolean check(int cur, int cnt) {
		boolean result = false;
		if (cnt == 4) {
			return true;
		}
		visited[cur] = true;

		for (Integer next : graph[cur]) {
			if (!visited[next]) {
				result = check(next, cnt + 1);
				if (result) {
					return true;
				}
			}
		}

		visited[cur] = false;
		return false;
	}

}
