package com.baekjoon.problem42;

public class HN_등굣길 {
	public int solution(int m, int n, int[][] puddles) {
		int answer = 0;
		int dp[][] = new int[n + 1][m + 1];
		boolean pu[][] = new boolean[n + 1][m + 1];
		for (int l = 0; l < puddles.length; l++) {
			pu[puddles[l][1]][puddles[l][0]] = true;
		}

		for (int r = 1; r <= n; r++) {
			if (pu[r][1])
				break;
			dp[r][1] = 1;
		}

		for (int c = 1; c <= m; c++) {
			if (pu[1][c])
				break;
			dp[1][c] = 1;
		}

		dp[n][m] = 0;
		for (int r = 2; r <= n; r++) {
			for (int c = 2; c <= m; c++) {
				if (pu[r][c]) {
					continue;
				}

				int ta = pu[r - 1][c] ? 0 : dp[r - 1][c];
				int tb = pu[r][c - 1] ? 0 : dp[r][c - 1];
				dp[r][c] = (ta + tb) % 1_000_000_007;
			}
		}

		answer = dp[n][m];
		return answer;
	}
}
