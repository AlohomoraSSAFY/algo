package com.baekjoon.problem21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ2615 {
	static final int L = 19;
	static int answer, y, x, map[][];
	static int dx[] = { 0, 1, 1, 1 }; // 우상, 우, 우하, 하 탐색
	static int dy[] = { 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = 0;
		y = x = -1;

		map = new int[L][L];

		StringTokenizer st;
		for (int h = 0; h < L; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < L; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
			}
		}

		find: for (int h = 0; h < L; h++) {
			for (int w = 0; w < L; w++) {
				if (map[h][w] != 0) {
					int tmp = map[h][w];
					for (int d = 0; d < 4; d++) {
						int ny = h;
						int nx = w;
						int cnt = 0;

						while (bound(ny, nx) && map[ny][nx] == tmp) {
							cnt++;
							ny += dy[d];
							nx += dx[d];
						}

						if (cnt == 5) {
							// reverse 체크
							ny -= dy[d];
							nx -= dx[d];

							while (bound(ny, nx) && map[ny][nx] == tmp) {
								cnt--;
								ny -= dy[d];
								nx -= dx[d];
							}

							if (cnt == 0) {
								answer = tmp;
								y = h;
								x = w;
								break find;
							}
						} // if
					} // for
				} // if
			} // for
		} // for

		System.out.println(answer);
		if (y != -1 && x != -1)
			System.out.println((y + 1) + " " + (x + 1));
	}

	private static boolean bound(int ny, int nx) {
		if (ny < L && ny >= 0 && nx < L && nx >= 0)
			return true;
		return false;
	}

}
