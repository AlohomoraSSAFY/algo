package com.baekjoon.problem44;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HN_BOJ21317 {
	static int N, K, answer;
	static int energy[][], dp[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		energy = new int[N][3];
		dp = new int[2][N + 1];
		Arrays.fill(dp[0], Integer.MAX_VALUE);
		Arrays.fill(dp[1], Integer.MAX_VALUE);
		StringTokenizer st;
		for (int n = 1; n <= N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 2; i++) {
				energy[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		K = Integer.parseInt(br.readLine());

		// cur, jump, energy
		move(1, 1, 0);
		answer = Math.min(dp[0][N], dp[1][N]);
		System.out.println(answer);
	}

	private static void move(int cur, int jump, int esum) {
		dp[jump][cur] = esum;
		if (cur == N) {
			return;
		}

		for (int i = 1; i < 3; i++) {
			int next = cur + i;
			if (next <= N && dp[jump][next] > esum + energy[cur][i]) {
				move(next, jump, esum + energy[cur][i]);
			}
		}
		int next = cur + 3;
		if (next <= N && jump == 1 && dp[0][next] > esum + K) {
			move(next, 0, esum + K);
		}
	}

}
