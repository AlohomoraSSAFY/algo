package com.baekjoon.problem000;

import java.util.*;

public class HN_방문길이 {
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { -1, 0, 1, 0 };
	static boolean visited[][][];

	public int solution(String dirs) {
		int answer = 0;
		visited = new boolean[11][11][4];
		char dir[] = dirs.toCharArray();
		int x = 5;
		int y = 5;
		for (int i = 0; i < dir.length; i++) {
			int d = -1;
			switch (dir[i]) {
			case 'U':
				d = 0;
				break;
			case 'R':
				d = 1;
				break;
			case 'D':
				d = 2;
				break;
			case 'L':
				d = 3;
				break;
			}

			if ((y == 0 && d == 0) || (y == 10 && d == 2) || (x == 0 && d == 3) || (x == 10 && d == 1))
				continue;

			if (!visited[y][x][d]) {
				int nd = d >= 2 ? d - 2 : d + 2;
				visited[y][x][d] = visited[y + dy[d]][x + dx[d]][nd] = true;
				answer++;
				// System.out.println(y + " " + x + " " + d);
			}

			y += dy[d];
			x += dx[d];
		}

		return answer;
	}
}
