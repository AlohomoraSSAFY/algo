package com.baekjoon.problem38;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ16197 {
	static int N, M, answer;
	static char map[][];
	static boolean c1, c2;
	static Queue<int[]> q1;
	static Queue<int[]> q2;
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		q1 = new LinkedList<>();
		q2 = new LinkedList<>();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int n = 0; n < N; n++) {
			char tmp[] = br.readLine().toCharArray();
			for (int m = 0; m < M; m++) {
				map[n][m] = tmp[m];
				if (map[n][m] == 'o') {
					map[n][m] = '.';
					if (q1.size() == 0) {
						q1.add(new int[] { n, m });
					} else {
						q2.add(new int[] { n, m });
					}
				}
			}
		}

		answer = Integer.MAX_VALUE;

		bfs();
		
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);
	}

	private static void bfs() {
		int level = 0;
		while (!q1.isEmpty()) {
			level++;

			if (level > answer) {
				return;
			}

			if (level > 10) {
				return;
			}

			int s1 = q1.size();
			for (int s = 0; s < s1; s++) {
				int tmp1[] = q1.poll();
				int tmp2[] = q2.poll();

				for (int i = 0; i < 4; i++) {
					int ttmp1[] = tmp1.clone();
					int ttmp2[] = tmp2.clone();
					c1 = moveCoin(ttmp1, i);
					c2 = moveCoin(ttmp2, i);

					if (c1 && c2) {
						continue;
					} else if (c1 || c2) {
						answer = level;
						return;
					} else {
						q1.add(ttmp1);
						q2.add(ttmp2);
					}
				}
			}
		}
	}

	private static boolean moveCoin(int[] tmp, int i) {
		boolean result = false;

		int ny = tmp[0] + dy[i];
		int nx = tmp[1] + dx[i];

		if (!bound(ny, nx)) {
			// 동전 떨어짐
			result = true;
		} else if (map[ny][nx] != '#') {
			tmp[0] = ny;
			tmp[1] = nx;
		}

		return result;
	}

	private static boolean bound(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= N || nx >= M)
			return false;
		return true;
	}

}
