package com.baekjoon.problem55;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ19942 {
	static int N, cost;
	static boolean answer[];
	static boolean selected[];
	static int input[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N + 1][5];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			input[0][i] = Integer.parseInt(st.nextToken());
		}
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 5; i++) {
				input[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		selected = new boolean[N + 1];
		answer = new boolean[N + 1];
		cost = Integer.MAX_VALUE;
		subSet(1, 0, 0, 0, 0, 0);

		StringBuilder sb = new StringBuilder();
		if (cost == Integer.MAX_VALUE) {
			sb.append(-1).append("\n");
		} else {
			sb.append(cost).append("\n");
			for (int i = 0; i < answer.length; i++) {
				if (answer[i])
					sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}

	private static void subSet(int i, int mp, int mf, int ms, int mv, int c) {
		if (mp >= input[0][0] && mf >= input[0][1] && ms >= input[0][2] && mv >= input[0][3]) {
			if (c < cost) {
				cost = c;
				answer = selected.clone();
			}
			return;
		}

		if (i > N || c >= cost) {
			return;
		}

		selected[i] = true;
		subSet(i + 1, mp + input[i][0], mf + input[i][1], ms + input[i][2], mv + input[i][3], c + input[i][4]);

		selected[i] = false;
		subSet(i + 1, mp, mf, ms, mv, c);
	}

}
