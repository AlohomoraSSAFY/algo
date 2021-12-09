package com.baekjoon.problem37;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ1261 {
	static int N, M;
	static int map[][];
	static int dp[][];
	static boolean visited[][];
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };

	static class Point implements Comparable<Point> {
		int y, x, w;

		public Point(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}

		public int compareTo(Point p) {
			return Integer.compare(this.w, p.w);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		for (int n = 0; n < N; n++) {
			char input[] = br.readLine().toCharArray();
			Arrays.fill(dp[n], Integer.MAX_VALUE);
			for (int m = 0; m < M; m++) {
				map[n][m] = input[m] - '0';
			}
		} // for

		find();

		System.out.println(dp[N - 1][M - 1]);
	}

	private static void find() {
		visited = new boolean[N][M];
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0));
		dp[0][0] = 0;
		visited[0][0] = true;

		while (!pq.isEmpty()) {
			Point p = pq.poll();

			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];

				if (!bound(ny, nx) || visited[ny][nx])
					continue;

				visited[ny][nx] = true;
				if (map[ny][nx] == 1) {
					// 벽
					dp[ny][nx] = p.w + 1;
					pq.add(new Point(ny, nx, p.w + 1));
				} else {
					if (ny == N - 1 && nx == M - 1) {
						dp[ny][nx] = p.w;
						return;
					} else {
						// 지나갈 수 있음
						dp[ny][nx] = p.w;
						pq.add(new Point(ny, nx, p.w));
					}
				}
			}
		}
	}

	private static boolean bound(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= N || nx >= M)
			return false;
		return true;
	}

}
