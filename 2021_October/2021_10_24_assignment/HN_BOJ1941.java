package com.baekjoon.problem27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class HN_BOJ1941_2 {
	static final int N = 5;
	static int answer;
	static char map[][];
	static int dx[] = { 1, 0, -1, 0 }; // 우, 하, 좌, 상
	static int dy[] = { 0, 1, 0, -1 };
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][N];
		for (int h = 0; h < N; h++) {
			map[h] = br.readLine().toCharArray();
		}

		answer = 0;
		visited = new boolean[N][N];
		Combi(0, 0, 0, visited);

		System.out.println(answer);
	}

	private static void Combi(int cnt, int start, int sp, boolean[][] v) {
		if (7 - cnt + sp < 4) {
			return;
		}

		if (cnt == 7) {
			// 소문난 칠공주 결성
			int p = start - 1;
			boolean con = bfs(p, v);
			
			if (con)
				answer++;
			return;
		}

		for (int i = start; i < 25; i++) {
			int y = i / N;
			int x = i % N;

			v[y][x] = true;

			if (map[y][x] == 'S') {
				Combi(cnt + 1, i + 1, sp + 1, v);
			} else {
				Combi(cnt + 1, i + 1, sp, v);
			}

			v[y][x] = false;
		}
	}

	private static boolean bfs(int p, boolean[][] v) {
		boolean v2[][] = new boolean[N][N];
		v2[p / N][p % N] = true;

		Queue<Integer> q = new LinkedList<>();
		q.add(p);

		int length = 1;

		while (!q.isEmpty()) {
			int tmp = q.poll();

			for (int d = 0; d < 4; d++) {
				int ny = tmp / N + dy[d];
				int nx = tmp % N + dx[d];

				if (check(ny, nx, v) && !v2[ny][nx]) {
					length++;
					v2[ny][nx] = true;
					int np = ny * 5 + nx;
					q.add(np);
				}
			}
		}

		if (length != 7) {
			return false;
		}

		return true;
	}

	private static boolean check(int ny, int nx, boolean[][] v) {
		if (ny < 0 || ny >= N || nx < 0 || nx >= N || !v[ny][nx])
			return false;

		return true;
	}

}
