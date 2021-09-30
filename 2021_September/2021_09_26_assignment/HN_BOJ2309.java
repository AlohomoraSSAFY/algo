package com.baekjoon.problem18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HN_BOJ2309 {
	static final int N = 9, M = 7, G = 100;
	static int input[] = new int[N];
	static int selected[] = new int[M];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		combi(0, 0, 0);

		Arrays.sort(selected);
		for (int i = 0; i < M; i++) {
			System.out.println(selected[i]);
		}
	}

	private static boolean combi(int cnt, int start, int sum) {
		boolean result = false;
		if (sum > G) {
			return result;
		}

		if (cnt == M) {
			if (sum == G) {
				result = true;
			}

			return result;
		}
		for (int i = start; i < N; i++) {
			selected[cnt] = input[i];
			result = combi(cnt + 1, i + 1, sum + input[i]);

			if (result)
				return result;
		}

		return result;
	}
}
