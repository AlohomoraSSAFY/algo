package com.baekjoon.problem58;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ16724 {
	static int visited[][];
	static char map[][];
	static int N, M, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new int[N][M];
		map = new char[N][M];

		for (int n = 0; n < N; n++) {
			map[n] = br.readLine().toCharArray();
		}

		answer = 0;
		int cnt = 1;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (visited[n][m] == 0) {
					dfs(n, m, cnt++);
				}
			}
		}

		System.out.println(answer);
	}

	private static void dfs(int r, int c, int mark) {
		visited[r][c] = mark;

		int nr = r, nc = c;
		switch (map[r][c]) {
		case 'D':
			nr++;
			break;
		case 'L':
			nc--;
			break;
		case 'U':
			nr--;
			break;
		case 'R':
			nc++;
			break;
		}

		if (visited[nr][nc] == 0) {
			dfs(nr, nc, mark);
		} else if (visited[nr][nc] == mark) {
			answer++;
		}
		// 이미 찾은 순환선이라면 return;
	}

}
