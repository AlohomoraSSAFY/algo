package com.baekjoon.problem54;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ1937 {
	static int N, answer, cnt;
	static int map[][], dp[][];
	static boolean visited[][];

	static class Point {
		int y, x, value;

		public Point(int y, int x, int value) {
			this.y = y;
			this.x = x;
			this.value = value;
		}
	}

	static int dy[] = { 0, 1, 0, -1 };
	static int dx[] = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		visited = new boolean[N][N];

		StringTokenizer st;
		PriorityQueue<Point> input = new PriorityQueue<Point>((p1, p2) -> {
			return Integer.compare(p1.value, p2.value);
		});

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				map[n][i] = Integer.parseInt(st.nextToken());
				input.add(new Point(n, i, map[n][i]));
			}
		}

		cnt = 0;
		answer = 0;
		while (!input.isEmpty() && cnt < N * N) {
			Point p = input.poll();

			if (dp[p.y][p.x] == 0) {
				dfs(p.y, p.x, 1);
			}
		}

		System.out.println(answer);
	}

	private static void dfs(int y, int x, int v) {
		PriorityQueue<Point> input = new PriorityQueue<Point>((p1, p2) -> {
			return Integer.compare(p1.value, p2.value);
		});

		dp[y][x] = v;
		answer = Math.max(answer, v);

		if (!visited[y][x]) {
			visited[y][x] = true;
			cnt++;
		}

		for (int i = 0; i < dx.length; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (bound(ny, nx) && map[ny][nx] > map[y][x] && dp[ny][nx] < v + 1) {
				input.add(new Point(ny, nx, map[ny][nx]));
			}
		}

		while (!input.isEmpty()) {
			Point p = input.poll();
			if (dp[p.y][p.x] < v + 1)
				dfs(p.y, p.x, v + 1);
		}
	}

	private static void find(int y, int x) {
		Queue<Point> q = new LinkedList<>();
		dp[y][x] = 1;
		q.add(new Point(y, x, dp[y][x]));

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < dx.length; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];

				if (bound(ny, nx) && map[ny][nx] > map[p.y][p.x] && dp[ny][nx] < p.value + 1) {
					dp[ny][nx] = p.value + 1;
					q.add(new Point(ny, nx, dp[ny][nx]));
				}
			}
		}
	}

	private static boolean bound(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= N || nx >= N)
			return false;
		return true;
	}

}
