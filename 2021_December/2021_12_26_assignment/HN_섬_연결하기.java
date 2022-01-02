package com.baekjoon.problem43;

import java.util.Arrays;
import java.util.Comparator;

public class HN_섬_연결하기 {
	static int parents[];

	public int solution(int n, int[][] costs) {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}

		Arrays.sort(costs, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[2], b[2]);
			}
		});
		
		int idx = 0;
		int cnt = 0;
		int cost = 0;
		while (true) {
			if (union(costs[idx][0], costs[idx][1])) {
				cost += costs[idx][2];

				if (++cnt == n - 1) {
					break;
				}
			}
			idx++;
		}

		return cost;
	}

	public boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa != pb) {
			parents[pa] = pb;
			return true;
		}

		return false;
	}

	public int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
}
