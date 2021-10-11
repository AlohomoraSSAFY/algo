package com.baekjoon.problem19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ9465 {
	static int TC, N, answer;
	static int[][] sticker;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			sticker = new int[2][N + 1];
			dp = new int[2][N + 1];
			answer = 0;
			for (int r = 0; r < 2; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= N; c++) {
					sticker[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			DP();

			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void DP() {
		if (N == 1) {
			answer = Math.max(sticker[0][1], sticker[1][1]);
		} else if (N == 2) {
			answer = Math.max(sticker[0][1] + sticker[1][2], sticker[1][1] + sticker[0][2]);
		} else {
			dp[0][1] = sticker[0][1]; dp[1][1] = sticker[1][1];
			dp[0][2] = sticker[1][1] + sticker[0][2];
			dp[1][2] = sticker[0][1] + sticker[1][2];
			for (int n = 3; n <= N; n++) {
				// 2번째 전값 계산하기
				int tmp1 = Math.max(dp[0][n-2], dp[1][n-2]);
				
				dp[0][n] = Math.max(tmp1 + sticker[0][n], dp[1][n-1] + sticker[0][n]);
				dp[1][n] = Math.max(tmp1 + sticker[1][n], dp[0][n-1] + sticker[1][n]);
			}
			answer = Math.max(dp[0][N], dp[1][N]);
		}
	}

}
