package com.baekjoon.problem54;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ20058 {
	static int N, Q, R, answer, max;
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int map[][], copyMap[][];
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		R = (int) Math.pow(2, N);
		map = new int[R][R];
		visited = new boolean[R][R];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < R; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int q = 0; q < Q; q++) {
			int temp = Integer.parseInt(st.nextToken());

//			if (temp == N) {
//				rotation2();
//				map = copy(copyMap);
//			} else 
			if (temp != 0) {
				// 회전
				rotation(temp);
				map = copy(copyMap);
			} else {
				copyMap = copy(map);
			}
			// 얼음 감소
			check();
		}

		max = 0;
		answer = 0;
		calculate();
		System.out.println(answer + "\n" + max);
	}

	private static void rotation2() {
		copyMap = new int[R][R];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < R; j++) {
				copyMap[j][R - i - 1] = map[i][j];
			}
		}
	}

	private static void calculate() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < R; c++) {
				if (!visited[r][c] && map[r][c] != 0) {
					bfs(r, c);
				}
			}
		}
	}

	private static void bfs(int r, int c) {
		int result = 0;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int y = temp[0];
			int x = temp[1];
			answer += map[y][x];
			result++;

			for (int i = 0; i < dx.length; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (!bound(ny, nx) || visited[ny][nx] || map[ny][nx] == 0) {
					continue;
				}

				visited[ny][nx] = true;
				q.add(new int[] { ny, nx });
			}
		}

		max = Math.max(max, result);
	}

	private static void rotation(int l) {
		copyMap = new int[R][R];
		int gab = (int) Math.pow(2, l);
		for (int i = 0; i < R; i = i + gab) {
			for (int j = 0; j < R; j = j + gab) {
				makeCopy(i, j, gab);
			}
		}
	}

	private static void makeCopy(int r, int c, int l) {
		int temp = 0;
		for (int i = r; i < r + l; i++) {
			int temp2 = r;
			for (int j = c; j < c + l; j++) {
				copyMap[temp2][c + l - temp - 1] = map[i][j];
				temp2++;
			}
			temp++;
		}

//		// 우측
//		for (int i = r; i < r + l; i++) {
//			for (int j = c; j < c + l; j++) {
//				copyMap[i][j + l] = map[i][j];
//			}
//		}
//
//		// 아래
//		for (int i = r; i < r + l; i++) {
//			for (int j = c + l; j < c + l * 2; j++) {
//				copyMap[i + l][j] = map[i][j];
//			}
//		}
//
//		// 좌측
//		for (int i = r + l; i < r + l * 2; i++) {
//			for (int j = c + l; j < c + l * 2; j++) {
//				copyMap[i][j - l] = map[i][j];
//			}
//		}
//
//		// 위
//		for (int i = r + l; i < r + l * 2; i++) {
//			for (int j = c; j < c + l; j++) {
//				copyMap[i - l][j] = map[i][j];
//			}
//		}
	}

	private static void check() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < R; c++) {
				int cnt = 0;
				if (copyMap[r][c] == 0) {
					continue;
				}

				for (int i = 0; i < dx.length; i++) {
					int ny = r + dy[i];
					int nx = c + dx[i];

					if (!bound(ny, nx)) {
						continue;
					}

					if (copyMap[ny][nx] > 0) {
						cnt++;
					}
				}

				if (cnt < 3) {
					map[r][c]--;
				}
			}
		}
	}

	private static boolean bound(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= R || nx >= R)
			return false;
		return true;
	}

	private static int[][] copy(int[][] map) {
		int copyMap[][] = new int[R][R];
		for (int r = 0; r < R; r++) {
			copyMap[r] = map[r].clone();
		}
		return copyMap;
	}

}
