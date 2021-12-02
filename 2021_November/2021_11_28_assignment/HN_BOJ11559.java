package com.baekjoon.problem35;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class HN_BOJ11559 {
	static final int W = 6, H = 12;
	static boolean crush;
	static int answer;
	static char map[][];
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[H][W];

		for (int h = 0; h < H; h++) {
			map[h] = br.readLine().toCharArray();
		}

		answer = 0;
		game();

		System.out.println(answer);
	}

	private static void game() {
		crush = true;

		while (crush) {
			crush = false;

			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					// 터트리기
					if (map[h][w] != '.') {
						bfs(h, w);
					}
				}
			}

			if (crush)
				answer++;

			// 공백 없애기
			remove(map);
		}

	}

	private static void remove(char[][] map) {
		Queue<Character> q = new LinkedList<>();

		for (int w = 0; w < W; w++) {
			for (int h = H-1; h >= 0; h--) {
				if (map[h][w] != '.')
					q.add(map[h][w]);
			}

			for (int h = H-1; h >= 0 ; h--) {
				if (!q.isEmpty()) {
					map[h][w] = q.poll();
				} else {
					map[h][w] = '.';
				}
			}
		}
	}

	private static void bfs(int y, int x) {
		char target = map[y][x];
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> q2 = new LinkedList<>();
		q.add(new int[] { y, x });
		q2.add(new int[] { y, x });
		boolean visited[][] = new boolean[H][W];
		visited[y][x] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			int tmp[] = q.poll();

			for (int i = 0; i < 4; i++) {
				int ny = tmp[0] + dy[i];
				int nx = tmp[1] + dx[i];

				if (check(ny, nx) && !visited[ny][nx] && map[ny][nx] == target) {
					q.add(new int[] { ny, nx });
					q2.add(new int[] { ny, nx });
					visited[ny][nx] = true;
					cnt++;
				}
			}
		}

		if (cnt >= 4) {
			crush = true;
			while (!q2.isEmpty()) {
				int tmp[] = q2.poll();
				map[tmp[0]][tmp[1]] = '.';
			}
		}
	}

	private static boolean check(int ny, int nx) {
		if (ny < 0 || ny >= H || nx < 0 || nx >= W)
			return false;
		return true;
	}

}
