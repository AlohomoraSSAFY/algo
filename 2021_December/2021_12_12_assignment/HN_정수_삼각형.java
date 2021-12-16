package com.baekjoon.problem39;

public class HN_정수_삼각형 {
	public int solution(int[][] triangle) {
		int answer = 0;
		int height = triangle.length;
		int dp[][] = new int[height][height];
		dp[0][0] = triangle[0][0];
		for (int i = 1; i < height; i++) {
			for (int j = 0; j < i; j++) {
				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + triangle[i][j]);
				dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i - 1][j] + triangle[i][j + 1]);
			}
		}
		for (int i = 0; i < height; i++) {
			answer = Math.max(answer, dp[height - 1][i]);
		}

		return answer;
	}
}
