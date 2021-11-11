package com.baekjoon.problem28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ2573 {
	static int N, M;

	static class Ice {
		int y, x, ws;

		public Ice(int y, int x, int ws) {
			this.y = y;
			this.x = x;
			this.ws = ws;
		}
	}

	static int map[][];
	static boolean visited[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	static Queue<Ice> ilist = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if (map[n][m] != 0)
					ilist.add(new Ice(n, m, 0));
			}
		}

		System.out.println(bfs());
	}

	private static int checkWS(Ice i, int s) {
		Queue<Ice> tmp = new LinkedList<>();
		visited = new boolean[N][M];

		tmp.add(i);
		visited[i.y][i.x] = true;
		int size = 1;
		while (!tmp.isEmpty()) {
			Ice ice = tmp.poll();

			int cnt = 0;
			for (int d = 0; d < 4; d++) {
				int ny = ice.y + dy[d];
				int nx = ice.x + dx[d];

				if (check(ny, nx)) {
					if (map[ny][nx] == 0) {
						cnt++;
					} else if (map[ny][nx] != 0 && !visited[ny][nx]) {
						visited[ny][nx] = true;
						tmp.add(new Ice(ny, nx, 0));
						size++;
					}
				}
			}

			ice.ws = cnt;
			ilist.add(ice);
		}
		
		if (size != s)
			return -1;

		return size;
	}

	private static boolean check(int ny, int nx) {
		if (ny >= N || nx >= M || ny < 0 || nx < 0)
			return false;
		return true;
	}

	private static int bfs() {
		int divided = -1;
		int level = -1;

		while (!ilist.isEmpty()) {
			level++;

			int size = ilist.size();
			Ice i = ilist.poll();
			ilist.clear();

			divided = checkWS(i, size);
			if (divided == -1)
				return level;

			melting(divided);
		}

		return 0;
	}

	private static void melting(int size) {
		// 빙산 녹이기
		for (int s = 0; s < size; s++) {
			Ice i = ilist.poll();
			map[i.y][i.x] = map[i.y][i.x] - i.ws < 0 ? 0 : map[i.y][i.x] - i.ws;

			if (map[i.y][i.x] != 0)
				ilist.add(i);
		}
	}

}
