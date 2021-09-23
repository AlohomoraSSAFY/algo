package com.baekjoon.problem16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ17070_1 {
	static int N, answer;
	static int map[][], dp[][][];
	static boolean visited[][][];
	static int[] dx = { 1, 1, 0 };
	static int[] dy = { 0, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N][3];
		visited = new boolean[N][N][3];
		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		answer = dfs(0, 1, 0);
		System.out.println(answer);
	}

	private static int dfs(int y, int x, int type) {
		visited[y][x][type] = true;

		if (y == N - 1 && x == N - 1) {
			return 1;
		}

		boolean checkY = false, checkX = false, checkXY = false;
		int ny = y + 1;
		int nx = x + 1;

		if (ny >= 0 && ny < N && map[ny][x] == 0)
			checkY = true;
		if (nx >= 0 && nx < N && map[y][nx] == 0)
			checkX = true;
		if (checkX && checkY && map[ny][nx] == 0)
			checkXY = true;

		switch (type) {
		case 0: // 가로
			if (checkX) {
				if (!visited[y][nx][0]) {
					dp[y][nx][0] = dfs(y, nx, 0);
				}

				dp[y][x][type] += dp[y][nx][0];
			}

			if (checkXY) {
				if (!visited[ny][nx][2]) {
					dp[ny][nx][2] = dfs(ny, nx, 2);
				}

				dp[y][x][type] += dp[ny][nx][2];
			}
			break;
		case 1: // 세로
			if (checkY) {
				if (!visited[ny][x][1]) {
					dp[ny][x][1] = dfs(ny, x, 1);
				}

				dp[y][x][type] += dp[ny][x][1];
			}

			if (checkXY) {
				if (!visited[ny][nx][2]) {
					dp[ny][nx][2] = dfs(ny, nx, 2);
				}

				dp[y][x][type] += dp[ny][nx][2];
			}
			break;
		case 2: // 대각선
			if (checkX) {
				if (!visited[y][nx][0]) {
					dp[y][nx][0] = dfs(y, nx, 0);
				}

				dp[y][x][type] += dp[y][nx][0];
			}

			if (checkY) {
				if (!visited[ny][x][1]) {
					dp[ny][x][1] = dfs(ny, x, 1);
				}

				dp[y][x][type] += dp[ny][x][1];
			}

			if (checkXY) {
				if (!visited[ny][nx][2]) {
					dp[ny][nx][2] = dfs(ny, nx, 2);
				}

				dp[y][x][type] += dp[ny][nx][2];
			}
			break;
		}

		return dp[y][x][type];
	}

}
