package com.baekjoon.problem58;

import java.util.*;

public class HN_땅따먹기 {
	int solution(int[][] land) {
		int answer = 0;
		int N = land.length;
		int[][] dp = new int[N][4];

		for (int c = 0; c < 4; c++) {
			dp[0][c] = land[0][c];
		}

		for (int n = 0; n < N - 1; n++) {
			for (int c = 0; c < 4; c++) {
				for (int nc = 0; nc < 4; nc++) {
					if (c == nc)
						continue;

					dp[n + 1][nc] = Math.max(dp[n + 1][nc], dp[n][c] + land[n + 1][nc]);
				}
			}
		}

		for (int c = 0; c < 4; c++) {
			answer = Math.max(dp[N - 1][c], answer);
		}

		return answer;
	}
}
