package com.baekjoon.problem26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ2096_2 {
	static int N, max, min;
	static int map[][];
	static int minDP[][], maxDP[][];
	static int dx[] = { -1, 0, 1 };
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		minDP = new int[N][3];
		maxDP = new int[N][3];

		StringTokenizer st;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			map[n][0] = Integer.parseInt(st.nextToken());
			map[n][1] = Integer.parseInt(st.nextToken());
			map[n][2] = Integer.parseInt(st.nextToken());
		}

		minDP[0][0] = maxDP[0][0] = map[0][0];
		minDP[0][1] = maxDP[0][1] = map[0][1];
		minDP[0][2] = maxDP[0][2] = map[0][2];

		for (int n = 1; n < N; n++) {
			minDP[n][0] = Math.min(minDP[n - 1][0] + map[n][0], minDP[n - 1][1] + map[n][0]);
			minDP[n][1] = Math.min(minDP[n - 1][0] + map[n][1], minDP[n - 1][1] + map[n][1]);
			minDP[n][1] = Math.min(minDP[n - 1][2] + map[n][1], minDP[n][1]);
			minDP[n][2] = Math.min(minDP[n - 1][1] + map[n][2], minDP[n - 1][2] + map[n][2]);

			maxDP[n][0] = Math.max(maxDP[n - 1][0] + map[n][0], maxDP[n - 1][1] + map[n][0]);
			maxDP[n][1] = Math.max(maxDP[n - 1][0] + map[n][1], maxDP[n - 1][1] + map[n][1]);
			maxDP[n][1] = Math.max(maxDP[n - 1][2] + map[n][1], maxDP[n][1]);
			maxDP[n][2] = Math.max(maxDP[n - 1][1] + map[n][2], maxDP[n - 1][2] + map[n][2]);
		}

		min = Math.min(minDP[N - 1][0], Math.min(minDP[N - 1][1], minDP[N - 1][2]));
		max = Math.max(maxDP[N - 1][0], Math.max(maxDP[N - 1][1], maxDP[N - 1][2]));

		StringBuilder sb = new StringBuilder();
		sb.append(max).append(" ").append(min);
		System.out.println(sb);
	}
}
