package com.baekjoon.problem55;

public class HN_삼각_달팽이 {
	static int map[][];
	static int dx[] = { 0, 1, -1 };
	static int dy[] = { 1, 0, -1 };

	public int[] solution(int n) {
		map = new int[n][n];
		int x = 0;
		int y = 0;
		int num = 1;
		int dir = 0;
		while (true) {
			map[y][x] = num++;
			int ny = y + dy[dir];
			int nx = x + dx[dir];

			if (n == 1)
				break;

			// 벽에 부딫히면 방향 바꾸기
			if (ny >= n || nx >= n || map[ny][nx] != 0) {
				dir = (dir + 1) % 3;
				ny = y + dy[dir];
				nx = x + dx[dir];
			}

			if (map[ny][nx] != 0) {
				break;
			}
			y = ny;
			x = nx;
		}
		int[] answer = new int[n * (n + 1) / 2];
		int idx = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c <= r; c++) {
				answer[idx++] = map[r][c];
			}
		}
		return answer;
	}

}
