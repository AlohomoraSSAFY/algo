package com.baekjoon.problem25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ10971 {
	static int N, answer, start;
	static int W[][];
	static boolean sel[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		sel = new boolean[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// Permu
		answer = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			start = i;
			sel[i] = true;
			Permu(i, 1, 0);
			sel[i] = false;
		}

		System.out.println(answer);
	}

	private static void Permu(int old, int cnt, int sum) {
		if (sum > answer)
			return;

		if (cnt == N) {
			if (W[old][start] == 0)
				return;
			answer = Math.min(answer, sum + W[old][start]);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (sel[i] || W[old][i] == 0)
				continue;
			sel[i] = true;
			Permu(i, cnt + 1, sum + W[old][i]);
			sel[i] = false;
		}
	}

}
