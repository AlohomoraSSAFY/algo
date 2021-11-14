package com.baekjoon.problem32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 16460kb 156ms
public class Other_BOJ14391 {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] input = reader.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			String in = reader.readLine();
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(in.charAt(j) + "");
		}
		visit = new boolean[n][m];
		dfs(0, 0);
		System.out.println(max);
	}

	static int n, m;
	static int[][] arr;
	static int max = Integer.MIN_VALUE;
	static boolean[][] visit;

	static void dfs(int depth, int sum) {
		if (depth == n * m) {
			max = Math.max(max, sum);
			return;
		}
		int x = depth / m;
		int y = depth % m;
		if (visit[x][y]) // 방문을 한경우
			dfs(depth + 1, sum);
		else {
			int val, nx, ny;
			val = arr[x][y];
			visit[x][y] = true;
			dfs(depth + 1, sum + val);
			visit[x][y] = false;

			// 밑
			for (int k = 1; k < n - x; k++) {
				nx = x + k;
				ny = y;
				if (visit[nx][ny])
					return;
				val *= 10;
				val += arr[nx][ny];
				for (int j = 1; j <= k; j++)
					visit[x + j][ny] = true;
				dfs(depth + 1, sum + val);
				for (int j = 1; j <= k; j++)
					visit[x + j][ny] = false;
			}
			val = arr[x][y];
			// 오른쪽
			for (int k = 1; k < m - y; k++) {
				nx = x;
				ny = y + k;
				if (visit[nx][ny])
					return;
				val *= 10;
				val += arr[nx][ny];
				for (int j = 1; j <= k; j++)
					visit[nx][y + j] = true;
				dfs(depth + 1, sum + val);
				for (int j = 1; j <= k; j++)
					visit[nx][y + j] = false;
			}
		}
	}

}
