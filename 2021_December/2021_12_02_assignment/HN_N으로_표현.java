package com.baekjoon.problem36;

public class HN_N으로_표현 {
	static final int MAX = 1000000;
	static final int target = 32001;
	static int dp[];
	static int case1[], case2[];

	public int solution(int N, int number) {
		dp = new int[MAX];
		case1 = new int[8];
		case2 = new int[8];
		int tmp = 0;
		for (int i = 1; i <= 7; i++) {
			tmp = tmp * 10 + N;
			if (tmp > MAX - 1)
				break;
			dp[tmp] = i;
			case1[i] = tmp;
		}

		for (int f = 1; f < 8; f++) {
			for (int s = 1; s < 8; s++) {
				if (dp[case1[f]] + dp[case1[s]] > 8)
					continue;
				if (case1[s] == 0)
					continue;
				cal(case1[f], case1[s]);
			}
		}

		find();

		int answer = dp[number];
		if (answer == 0)
			return -1;
		return answer;
	}

	public static void find() {
		for (int f = 1; f < MAX; f++) {
			if (dp[f] == 0)
				continue;
			for (int s = 1; s < MAX; s++) {
				if (dp[s] == 0)
					continue;
				if (dp[f] + dp[s] > 8)
					continue;

				cal(f, s);
			} // for
		}
	}// function

	public static void cal(int f, int s) {
		for (int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				int plus = f + s;

				if (plus > target - 1) {
					continue;
				}

				if (dp[plus] <= 0 || dp[plus] > dp[f] + dp[s]) {
					dp[plus] = dp[f] + dp[s];
				}

				break;
			case 1:
				int minus = f - s;

				if (minus <= 0) {
					continue;
				}

				if (dp[minus] <= 0 || dp[minus] > dp[f] + dp[s]) {
					dp[minus] = dp[f] + dp[s];
				}

				break;
			case 2:
				int mux = f * s;
				if (mux > target - 1) {
					continue;
				}

				if (dp[mux] <= 0 || dp[mux] > dp[f] + dp[s]) {
					dp[mux] = dp[f] + dp[s];
				}

				break;
			case 3:
				int div = f / s;

				if (div <= 0) {
					continue;
				}

				if (dp[div] <= 0 || dp[div] > dp[f] + dp[s]) {
					dp[div] = dp[f] + dp[s];
				}
				break;
			}
		} // for
	}
}
