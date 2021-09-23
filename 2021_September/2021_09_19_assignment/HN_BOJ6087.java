package com.baekjoon.problem16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ6087 {
	static int W, H, answer;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static char[][] map;
	static Mirror end, start;

	static class Mirror implements Comparable<Mirror> {
		int y, x, dir, m;

		public Mirror(int y, int x, int dir, int m) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.m = m;
		}

		@Override
		public int compareTo(Mirror m) {
			if (this.m == m.m) {
				if (this.x == m.x) {
					return Integer.compare(this.y - end.y, m.y - end.y);
				}
				return Integer.compare(this.x - end.x, m.x - end.x);
			}
			return this.m - m.m;
		}
	}

	static int[][] dp;
	static boolean visited[][][];
	static PriorityQueue<Mirror> q = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		dp = new int[H][W];
		visited = new boolean[H][W][4];
		boolean check = false;
		for (int h = 0; h < H; h++) {
			String tmp = br.readLine();
			for (int w = 0; w < W; w++) {
				map[h][w] = tmp.charAt(w);
				if (map[h][w] == 'C') {
					if (check) {
						end = new Mirror(h, w, 0, 0);
					} else {
						check = true;
						start = new Mirror(h, w, 0, 0);
					}
				}
			}
		}

		answer = Integer.MAX_VALUE;
		bfs();
		System.out.println(answer);
	}

	private static void bfs() {
		// Mirror start = q.poll();
		// end = q.poll();

		// 시작점 방향을 체크
		for (int i = 0; i < 4; i++) {
			q.add(new Mirror(start.y, start.x, i, 0));
			visited[start.y][start.x][i] = true;
		}

		while (!q.isEmpty()) {
			Mirror tmpM = q.poll();

			if (tmpM.y == end.y && tmpM.x == end.x) {
				answer = Math.min(answer, tmpM.m);
				return;
			}

			for (int j = -1; j <= 1; j++) {
				int k = tmpM.dir + j;
				if (k >= 4) {
					k = 0;
				} else if (k < 0) {
					k = 3;
				}
				int ny = tmpM.y + dy[k];
				int nx = tmpM.x + dx[k];

				if (ny >= 0 && ny < H && nx >= 0 && nx < W && map[ny][nx] != '*') {
					int cnt = (k == tmpM.dir) ? tmpM.m : tmpM.m + 1;
					if (!visited[ny][nx][k]) {
						visited[ny][nx][k] = true;
						dp[ny][nx] = cnt;
						q.add(new Mirror(ny, nx, k, cnt));
					} else if (dp[ny][nx] > cnt) {
						dp[ny][nx] = cnt;
						q.add(new Mirror(ny, nx, k, cnt));
					}
				}
			}
		} // q 비움
	}
}