package com.baekjoon.problem55;

import java.util.*;

public class HN_배달 {
	static final int LIMIT = 1000000;
	static int time[][];

	public int solution(int N, int[][] road, int K) {

		time = new int[N + 1][N + 1];
		for (int n = 0; n <= N; n++) {
			Arrays.fill(time[n], LIMIT);
			time[n][n] = 0;
		}
		for (int l = 0; l < road.length; l++) {
			time[road[l][0]][road[l][1]] = time[road[l][1]][road[l][0]] = Math.min(road[l][2],
					time[road[l][0]][road[l][1]]);
		}

		for (int m = 1; m <= N; m++) {
			for (int s = 1; s <= N; s++) {
				if (m == s)
					continue;
				for (int e = 1; e <= N; e++) {
					if (m == e || s == e)
						continue;
					time[s][e] = Math.min(time[s][e], time[s][m] + time[m][e]);
				}
			}
		}

		int answer = 0;
		for (int n = 1; n <= N; n++) {
			if (time[1][n] <= K)
				answer++;
		}

		return answer;
	}
}
