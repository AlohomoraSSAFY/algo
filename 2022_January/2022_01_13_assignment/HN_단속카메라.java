package com.baekjoon.problem47;

import java.util.Arrays;
import java.util.Comparator;

public class HN_단속카메라 {
	public int solution(int[][] routes) {
		Arrays.sort(routes, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return Integer.compare(o2[0], o1[0]);
				}
				return Integer.compare(o1[1], o2[1]);
			}
		});
		int end = routes[0][1];
		int answer = 1;
		for (int l = 1; l < routes.length; l++) {
			if (routes[l][0] > end) {
				answer++;
				end = routes[l][1];
			}
		}

		return answer;
	}
}
