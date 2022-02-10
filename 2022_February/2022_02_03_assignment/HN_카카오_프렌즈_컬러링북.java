package com.baekjoon.problem50;

import java.util.LinkedList;
import java.util.Queue;

public class HN_카카오_프렌즈_컬러링북 {
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static boolean visited[][];
	static int M, N;
	static int Picture[][];

	public int[] solution(int m, int n, int[][] picture) {
		int[] answer = new int[2];
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		M = m;
		N = n;
		Picture = picture;
		visited = new boolean[m][n];
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (!visited[r][c] && picture[r][c] != 0) {
					numberOfArea++;
					int temp = bfs(r, c, picture[r][c]);
					if (maxSizeOfOneArea < temp) {
						maxSizeOfOneArea = temp;
					}
				}
			}
		}
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	public int bfs(int r, int c, int color) {
		int result = 1;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int temp[] = q.poll();

			for (int d = 0; d < dx.length; d++) {
				int nr = temp[0] + dy[d];
				int nc = temp[1] + dx[d];

				if (nr < 0 || nc < 0 || nr >= M || nc >= N)
					continue;

				if (!visited[nr][nc] && Picture[nr][nc] == color) {
					visited[nr][nc] = true;
					result++;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return result;
	}
}
