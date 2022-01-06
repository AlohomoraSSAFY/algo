package com.baekjoon.problem44;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ20057 {
	static int N, answer, dirCnt, end;
	static int map[][], tempMap[][];
	static int dx[][] = { { -1, 0, 1, 0 }, { -1, -1, 1, 1 }, { -2, 0, 2, 0 } };
	static int dy[][] = { { 0, 1, 0, -1 }, { -1, 1, 1, -1 }, { 0, 2, 0, -2 } };
	static double percent[][] = { { 0, 0.07, 0, 0.07 }, { 0.10, 0.10, 0.01, 0.01 }, { 0.05, 0.02, 0, 0.02 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		StringTokenizer st;
		end = 0;

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				end += map[r][c];
			}
		}

		answer = 0;
		dirCnt = 1;
		int start = (N + 1) / 2;
		game(start, start, 2, 1, 0);

		System.out.println(answer);
	}

	private static void game(int r, int c, int cnt, int gab, int mdir) {
		if ((r == 1 && c == 1) || answer == end) {
			return;
		}

		int nr = r + dy[0][mdir];
		int nc = c + dx[0][mdir];

		spreadSend(nr, nc, mdir);

		if (gab - 1 == 0) {
			if (cnt - 1 == 0) {
				dirCnt++;
				game(nr, nc, 2, dirCnt, (mdir + 1) % 4);
			} else {
				game(nr, nc, cnt - 1, dirCnt, (mdir + 1) % 4);
			}
		} else {
			game(nr, nc, cnt, gab - 1, mdir);
		}
	}

	private static void spreadSend(int r, int c, int mdir) {
		// percent를 -mdir 해서 넣는다.
		int scnt = 0;
		for (int i = 0; i < dx.length; i++) {
			for (int j = 0; j < dx[i].length; j++) {
				int nr = r + dy[i][j];
				int nc = c + dx[i][j];
				int ndir = j - mdir >= 0 ? j - mdir : j - mdir + 4;
				int nv = (int) Math.floor(map[r][c] * (percent[i][ndir]));

				scnt += nv;
				if (bound(nr, nc)) {
					map[nr][nc] += nv;
				} else {
					answer += nv;
				}
			}
		}

		int nr = r + dy[0][mdir];
		int nc = c + dx[0][mdir];
		int nv = map[r][c] - scnt;
		map[r][c] = 0;
		if (bound(nr, nc)) {
			map[nr][nc] += nv;
		} else {
			answer += nv;
		}

	}

	private static boolean bound(int nr, int nc) {
		if (nr <= 0 || nc <= 0 || nr > N || nc > N)
			return false;
		return true;
	}

}
