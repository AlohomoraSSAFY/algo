package com.baekjoon.problem56;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ1167 {
	static int N, end, max;
	static boolean visited[];
	static List<Point> list[];

	static class Point {
		int next, cost;

		public Point(int next, int cost) {
			super();
			this.next = next;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new LinkedList[N + 1];
		StringTokenizer st;
		for (int n = 1; n <= N; n++) {
			list[n] = new LinkedList<>();
		}

		int leaf = 0;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int cnt = 0;
			int cur = Integer.parseInt(st.nextToken());
			while (true) {
				int next = Integer.parseInt(st.nextToken());
				if (next != -1) {
					int cost = Integer.parseInt(st.nextToken());
					list[cur].add(new Point(next, cost));
				} else {
					if (cnt == 1) {
						leaf = cur;
					}
					break;
				}
				cnt++;
			}
		} // input end
		visited = new boolean[N + 1];
		dfs(leaf, 0);
		visited = new boolean[N + 1];
		dfs(end, 0);

		System.out.println(max);

	}

	private static void dfs(int cur, int sum) {
		visited[cur] = true;
		for (Point p : list[cur]) {
			if (!visited[p.next]) {
				dfs(p.next, sum + p.cost);
			}
		}

		if (max < sum) {
			max = sum;
			end = cur;
		}
	}

}
