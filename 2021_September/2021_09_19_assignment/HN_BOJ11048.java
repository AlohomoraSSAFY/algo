package com.baekjoon.problem16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ11048_2 {
	static int N, M, answer;
	static int[][] map, dp;
	static boolean[][] visited;
	static int dx[] = { 1, 0 };
	static int dy[] = { 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs(0, 0, map[0][0]);
		
		System.out.println(dp[N - 1][M - 1]);
	}

	private static void bfs(int y, int x, int val) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { y, x });
		visited[y][x] = true;
		dp[y][x] = val;

		while (!q.isEmpty()) {
			int[] tmp = q.poll();

			for (int i = 0; i < dx.length; i++) {
				int ny = tmp[0] + dy[i];
				int nx = tmp[1] + dx[i];

				if (ny >= 0 && nx >= 0 && ny < N && nx < M) {
					if (!visited[ny][nx]) {
						visited[ny][nx] = true;
						q.add(new int[] { ny, nx });
						dp[ny][nx] = dp[tmp[0]][tmp[1]] + map[ny][nx];
					} else if (dp[ny][nx] < dp[tmp[0]][tmp[1]] + map[ny][nx]) {
						dp[ny][nx] = dp[tmp[0]][tmp[1]] + map[ny][nx];
						q.add(new int[] { ny, nx });
					}
				}
			}
		}
	}

}
