package com.baekjoon.problem45;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ22944 {
	static int N, K, H, D, MAX, answer;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static boolean visited[];
	static int cost[][];
	static List<Point> plist = new LinkedList<>();
	static Point start, end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		for (int n = 0; n < N; n++) {
			char[] tmp = br.readLine().toCharArray();
			for (int r = 0; r < N; r++) {
				if (tmp[r] == '.') {
					continue;
				}

				if (tmp[r] == 'U') {
					plist.add(new Point(n, r));
				} else if (tmp[r] == 'S') {
					start = new Point(n, r);
				} else {
					end = new Point(n, r);
				}
			}
		}
		plist.add(start);
		plist.add(end);
		MAX = plist.size();
		cost = new int[MAX][MAX];
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				if (i == j)
					continue;
				Point p1 = plist.get(i);
				Point p2 = plist.get(j);
				cost[i][j] = cost[j][i] = Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
			}
		}
		answer = Integer.MAX_VALUE;
		visited = new boolean[MAX];
		visited[MAX - 2] = true;
		Permu(MAX - 2, H, 0, 0);
		System.out.println(answer = answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void Permu(int cur, int h, int dur, int move) {
		if (h <= 0 || move > answer) {
			return;
		}

		for (int i = 0; i < MAX; i++) {
			if (!visited[i] && h + dur >= cost[cur][i]) {
				if (i == MAX - 1) {
					answer = Math.min(answer, move + cost[cur][i]);
					continue;
				}
				visited[i] = true;
				int nh = h;
				if (dur < cost[cur][i]) {
					nh = h + dur - cost[cur][i] + 1;
				}
				Permu(i, nh, D - 1, move + cost[cur][i]);
				visited[i] = false;
			}
		}
	}

}
