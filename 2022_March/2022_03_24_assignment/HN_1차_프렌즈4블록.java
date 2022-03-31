package com.baekjoon.problem55;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class HN_1차_프렌즈4블록 {

	static char[][] input;

	public int solution(int m, int n, String[] board) {
		int answer = 0;
		int R = m;
		int C = n;
		input = new char[R][C];
		for (int r = 0; r < R; r++) {
			input[r] = board[r].toCharArray();
		}

		while (true) {
			boolean map[][] = new boolean[R][C];
			for (int r = 0; r < R - 1; r++) {
				for (int c = 0; c < C - 1; c++) {
					if (input[r][c] != '.') {
						if (input[r][c] == input[r + 1][c] && input[r][c] == input[r][c + 1]
								&& input[r][c] == input[r + 1][c + 1])
							map[r][c] = map[r + 1][c] = map[r][c + 1] = map[r + 1][c + 1] = true;
					}
				}
			}

			int cnt = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c]) {
						cnt++;
						input[r][c] = '.';
					}
				}
			}

			if (cnt == 0)
				break;
			answer += cnt;

			// input 이동시키기
			Queue<Character> q[] = new LinkedList[C];
			for (int c = 0; c < C; c++) {
				q[c] = new LinkedList<>();
				for (int r = R - 1; r >= 0; r--) {
					if (!map[r][c])
						q[c].add(input[r][c]);
				}
			}

			input = new char[R][C];
			for (int r = 0; r < R; r++) {
				Arrays.fill(input[r], '.');
			}
			for (int c = 0; c < C; c++) {
				int r = R - 1;
				while (!q[c].isEmpty()) {
					input[r--][c] = q[c].poll();
				}
			}
		}

		return answer;
	}

}
