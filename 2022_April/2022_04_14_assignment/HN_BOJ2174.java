package com.baekjoon.problem000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ2174 {
	static class Robot {
		int y, x, dir;

		public Robot(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

	static int[][] map;
	static int dy[] = { -1, 0, 1, 0 }; // N, E, S, W
	static int dx[] = { 0, 1, 0, -1 };
	static int A, B, N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		map = new int[B + 1][A + 1];

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		List<Robot> list = new LinkedList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = B - Integer.parseInt(st.nextToken()) + 1;

			list.add(new Robot(y, x, getDir(st.nextToken())));
			map[y][x] = n + 1;
		}

		String answer = "OK";
		check: for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int rnum = Integer.parseInt(st.nextToken());
			String cmd = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());

			Robot r = list.get(rnum - 1);
			switch (cmd) {
			// 왼쪽 회전
			case "L":
				r.dir -= (cnt % 4);
				if (r.dir < 0)
					r.dir += 4;
				list.set(rnum - 1, r);
				break;
			// 오른쪽 회전
			case "R":
				r.dir += (cnt % 4);
				if (r.dir >= 4)
					r.dir -= 4;
				list.set(rnum - 1, r);
				break;
			// 전진
			case "F":
				int ny = r.y;
				int nx = r.x;
				for (int i = 0; i < cnt; i++) {
					ny += dy[r.dir];
					nx += dx[r.dir];
					if (ny <= 0 || ny > B || nx <= 0 || nx > A) {
						// 벽과 충돌
						answer = "Robot " + rnum + " crashes into the wall";
						break check;
					}

					if (map[ny][nx] != 0) {
						// 로봇과 충돌
						answer = "Robot " + rnum + " crashes into robot " + map[ny][nx];
						break check;
					}
				}

				map[ny][nx] = map[r.y][r.x];
				map[r.y][r.x] = 0;
				r.y = ny;
				r.x = nx;
				list.set(rnum - 1, r);
				break;
			}
		}
		System.out.println(answer);
	}

	private static int getDir(String dir) {
		int result = -1;
		switch (dir) {
		case "N":
			result = 0;
			break;
		case "E":
			result = 1;
			break;
		case "S":
			result = 2;
			break;
		case "W":
			result = 3;
			break;
		}
		return result;
	}
}
