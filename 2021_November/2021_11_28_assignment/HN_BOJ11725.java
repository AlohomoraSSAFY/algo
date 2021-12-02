package com.baekjoon.problem35;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class HN_BOJ11725 {
	static int N;
	static int parents[];
	static LinkedList<Integer>[] node;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		node = new LinkedList[N + 1];
		parents = new int[N + 1];

		for (int n = 1; n <= N; n++) {
			node[n] = new LinkedList<>();
		}

		StringTokenizer st;
		int from, to;
		for (int n = 0; n < N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());

			node[from].add(to);
			node[to].add(from);
		}

		parents[1] = -1;
		dfs(1);

		StringBuilder sb = new StringBuilder();
		for (int n = 2; n <= N; n++) {
			sb.append(parents[n]).append("\n");
		}

		System.out.println(sb);
	}

	private static void dfs(int cur) {
		Iterator<Integer> iterator = node[cur].iterator();

		int next;
		while (iterator.hasNext()) {
			next = iterator.next();
			if (parents[next] == 0) {
				parents[next] = cur;
				dfs(next);
			}
		}
	}

}
