package com.baekjoon.problem35;

public class HN_소수찾기 {
	static final int MAX = 10000001;
	static int selected[];
	static boolean flag[];
	static boolean oddNum[] = new boolean[MAX];
	static boolean visited[];
	static int len, result, max;

	public int solution(String numbers) {
		visited = new boolean[MAX];
		char input[] = numbers.toCharArray();
		len = input.length;

		oddNum[0] = oddNum[1] = true;
		for (int i = 2; i < MAX; i++) {
			if (oddNum[i])
				continue;

			for (int j = 2; j * i < MAX; j++) {
				oddNum[j * i] = true;
			}
		}

		int answer = 0;
		for (int l = 1; l <= len; l++) {
			result = 0;
			selected = new int[l];
			max = l;
			// cnt, start, input
			combi(0, 0, input);
			answer += result;
		}

		return answer;
	}

	public static void combi(int cnt, int start, char[] input) {
		if (cnt == max) {
			flag = new boolean[max];
			// cnt, sum , max, selected
			permu(0, 0, selected);
			return;
		}

		for (int i = start; i < len; i++) {
			selected[cnt] = (input[i] - '0');
			combi(cnt + 1, i + 1, input);
		}
		return;
	}

	public static void permu(int cnt, int sum, int[] input) {
		if (cnt == max) {
			if (!oddNum[sum] && !visited[sum]) {
				visited[sum] = true;
				result++;
			}
			return;
		}

		for (int i = 0; i < max; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			permu(cnt + 1, sum * 10 + input[i], input);
			flag[i] = false;
		}
		return;
	}
}
