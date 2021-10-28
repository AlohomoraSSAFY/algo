package com.baekjoon.problem24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class HN_BOJ16954 {
	static char map[][];
	static int answer;
	static final int N = 8;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int dx[] = { 0, 1, 1, 1, 0, 0, -1, -1, -1 };
	static int dy[] = { 0, -1, 0, 1, -1, 1, 1, 0, -1 };
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		answer = 0;
		bfs();

		System.out.println(answer);
	}

	private static void bfs() {
		q.add(new Point(N-1, 0));
		int level = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			level++;

			for (int i = 0; i < size; i++) {
				Point p = q.poll();
				
				if(map[p.y][p.x]=='#')
					continue;
				
				for (int j = 0; j < dx.length; j++) {
					int ny = p.y + dy[j];
					int nx = p.x + dx[j];

					if (check(ny, nx, map)) {
						if (answer == 0 && ny == 0 && nx == N - 1) {
							answer = 1;
							return;
						} else {
							q.add(new Point(ny, nx));
						} // else
					}
				} // for
			} // for
			
			if (level < N) {
				map = moveMap(map);
			}
		} // while
	}

	private static boolean check(int ny, int nx, char[][] m) {
		if (ny < N && ny >= 0 && nx < N && nx >= 0 && m[ny][nx] == '.') {
			return true;
		}
		return false;
	}

	private static char[][] moveMap(char[][] m) {
		char cmap[][] = new char[N][N];
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				cmap[i + 1][j] = m[i][j];
			}
		}
		Arrays.fill(cmap[0], '.');

		return cmap;
	}
}
