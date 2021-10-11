package com.baekjoon.problem20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class HN_BOJ14890 {
	static int N, L, answer;
	static int map[][];

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static boolean visited[][];
	static Queue<Point> leftPoint = new LinkedList<>();
	static Queue<Point> upPoint = new LinkedList<>();
	static int dy[] = { 1, -1, 0, 0 }; // 하, 상, 우, 좌
	static int dx[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int h = 0; h < N; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < N; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			// 열 좌표 추가 (위쪽 시작)
			upPoint.add(new Point(0, i));

			// 행 좌표 추가 (왼쪽 시작)
			leftPoint.add(new Point(i, 0));
		} // for

		rcCheck();

		System.out.println(answer);
	}

	private static void rcCheck() {
		visited = new boolean[N][N];

		answer = 0;
		answer += right(leftPoint, visited);

		visited = new boolean[N][N];

		answer += down(upPoint, visited);
	}

	private static int right(Queue<Point> lp, boolean[][] v) {
		int result = 0;
		while (!lp.isEmpty()) {
			Point p1 = lp.poll();

			boolean bridge = false;
			int flat = 1;
			int cnt = 0;
			int y = p1.y;
			int nx = p1.x;
			right: while (nx < N - 1) {
				int gab = map[y][nx] - map[y][nx + 1];
				if (v[y][nx + 1] || Math.abs(gab) > 1)
					break right;

				if (gab == 0) {
					if (bridge) {
						v[y][nx + 1] = true;
						cnt++;
						if (cnt >= L) {
							bridge = false;
							cnt = 0;
						}
					}
					flat++;
				} else if (gab == 1) {
					if (!bridge) {
						bridge = true;
						v[y][nx + 1] = true;
						cnt = 1;
						flat = 1;

						if (cnt >= L) {
							bridge = false;
							cnt = 0;
						}
					} else if (bridge) {
						break right;
					}
				} else {
					if (!bridge && flat >= L && checkHorizon(y, nx, v)) {
						flat = 1;
						Arrays.fill(v[y], nx - L + 1, nx + 1, true);
					} else {
						break right;
					}
				}

				nx++;
			}

			if (!bridge && nx == N - 1) {
				result++;
			} else {
				Arrays.fill(v[y], false);
			}
		}
		return result;
	}

	private static boolean checkHorizon(int y, int nx, boolean[][] v) {
		for (int i = nx; i > nx - L; i--) {
			if (v[y][i])
				return false;
		}
		return true;
	}

	private static int down(Queue<Point> uq, boolean[][] v) {
		int result = 0;
		while (!uq.isEmpty()) {
			Point p1 = uq.poll();

			boolean bridge = false;
			int flat = 1;
			int cnt = 0;
			int ny = p1.y;
			int x = p1.x;
			down: while (ny < N - 1) {
				int gab = map[ny][x] - map[ny + 1][x];
				if (v[ny + 1][x] || Math.abs(gab) > 1)
					break down;

				if (gab == 0) {
					if (bridge) {
						v[ny + 1][x] = true;
						cnt++;
						if (cnt >= L) {
							bridge = false;
							cnt = 0;
						}
					}
					flat++;
				} else if (gab == 1) {
					if (!bridge) {
						bridge = true;
						v[ny + 1][x] = true;
						cnt = 1;
						flat = 1;

						if (cnt >= L) {
							bridge = false;
							cnt = 0;
						}
					} else if (bridge) {
						break down;
					}
				} else {
					if (!bridge && flat >= L && checkVertical(ny, x, v)) {
						flat = 1;
						for (int i = ny - L + 1; i < ny + 1; i++) {
							v[i][x] = true;
						}
					} else {
						break down;
					}
				}

				ny++;
			}

			if (!bridge && ny == N - 1) {
				result++;
			} else {
				for (int i = 0; i < N; i++) {
					v[i][x] = false;
				}
			}
		}
		return result;
	}

	private static boolean checkVertical(int ny, int x, boolean[][] v) {
		for (int i = ny; i > ny - L; i--) {
			if (v[i][x])
				return false;
		}
		return true;
	}

}
