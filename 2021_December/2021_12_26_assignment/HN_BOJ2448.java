package com.baekjoon.problem43;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HN_BOJ2448 {
	static int N, K;
	static StringBuilder sb[];
	static boolean tree[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb = new StringBuilder[N];
		tree = new boolean[N][2 * N + 1];
		for (int n = 0; n < N; n++) {
			sb[n] = new StringBuilder();
		}
		K = 0;
		if (N != 3) {
			int cnt = N / 3;
			K = 1;
			while (Math.pow(2, K) != cnt) {
				K++;
			}
		}
		// 시작 depth, y, x
		dfs2(0, 0, N);

		print();
	}

	private static void dfs2(int depth, int y, int x) {
		if (depth == K) {
			dfs(y, x);
			return;
		}
		int add = N / (int) Math.pow(2, depth + 1);
		dfs2(depth + 1, y, x);
		dfs2(depth + 1, y + add, x + add);
		dfs2(depth + 1, y + add, x - add);
	}

	private static void print() {
		StringBuilder answer = new StringBuilder();
		for (int n = 0; n < N; n++) {
			for (int i = 1; i <= 2 * N; i++) {
				if (tree[n][i]) {
					answer.append("*");
				} else {
					answer.append(" ");
				}
			}
			answer.append("\n");
		}
		answer.setLength(answer.length() - 1);
		System.out.println(answer);
	}

	private static void dfs(int line, int middle) {
		for (int i = middle - 2; i <= middle + 2; i++) {
			if (i == middle) {
				tree[line][i] = true;
			}

			if (i == middle - 1 || i == middle + 1) {
				tree[line + 1][i] = true;
			}

			tree[line + 2][i] = true;
		}
	}

}
