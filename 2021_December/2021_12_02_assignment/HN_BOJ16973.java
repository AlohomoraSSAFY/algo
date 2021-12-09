package com.baekjoon.problem36;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ16973 {
	static int N, M;
	static int H, W, SR, SC, FR, FC, answer;
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	static int map[][], subSum1[][], subSum2[][];
	static boolean visited[][];
	static PriorityQueue<Point> pq = new PriorityQueue<>();

	static class Point implements Comparable<Point> {
		int Y, X, V;

		public Point(int y, int x, int v) {
			Y = y;
			X = x;
			V = v;
		}

		@Override
		public int compareTo(Point p) {
			return Integer.compare(this.V, p.V);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		subSum1 = new int[N + 1][M + 1];
		subSum2 = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 1; m <= M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				subSum1[n][m] = map[n][m] + subSum1[n][m - 1];
			}
		}

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		SR = Integer.parseInt(st.nextToken());
		SC = Integer.parseInt(st.nextToken());
		FR = Integer.parseInt(st.nextToken());
		FC = Integer.parseInt(st.nextToken());

		for (int h = 1; h <= N; h++) {
			for (int w = 1; w <= M - W + 1; w++) {
				subSum2[h][w] = subSum1[h][w + W - 1] - subSum1[h][w - 1];
			}
		}

		map = new int[N + 1][M + 1];
		for (int w = 1; w <= M - W + 1; w++) {
			for (int h = 1; h <= H; h++) {
				map[1][w] += subSum2[h][w];
			}
			for (int h = H + 1; h <= N; h++) {
				map[h - H + 1][w] = map[h - H][w] + subSum2[h][w] - subSum2[h - H][w];
			}
			for (int h = N - H + 2; h <= N; h++) {
				map[h][w] = -1;
			}
		}

		for (int w = M - W + 2; w <= M; w++) {
			for (int h = 1; h <= N; h++) {
				map[h][w] = -1;
			}
		}

		answer = -1;
		if (SR == FR && SC == FC) {
			answer = 0;
		} else {
			game();
		}

		System.out.println(answer);
	}

	private static void game() {
		pq.add(new Point(SR, SC, 0));
		visited[SR][SC] = true;

		while (!pq.isEmpty()) {
			int size = pq.size();

			for (int s = 0; s < size; s++) {
				Point tmp = pq.poll();

				for (int i = 0; i < 4; i++) {
					int ny = tmp.Y + dy[i];
					int nx = tmp.X + dx[i];
					int level = tmp.V;

					if (check1(ny, nx) && !visited[ny][nx] && map[ny][nx] == 0) {
						visited[ny][nx] = true;
						pq.add(new Point(ny, nx, level + 1));

						if (ny == FR && nx == FC) {
							answer = level + 1;
							return;
						}
					}
				} // for
			} // for
		} // while
	}

	// 범위 체크 오류...
	private static boolean check1(int ny, int nx) {
		if (ny < 1 || nx < 1 || ny + H - 1 > N || nx + W - 1 > M)
			return false;

		return true;
	}
}
