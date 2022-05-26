package com.baekjoon.problem59;

import java.util.Arrays;

public class HN_1차_비밀지도 {
	static int N;

	public String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		Arrays.fill(answer, "");
		N = n;
		for (int i = 0; i < N; i++) {
			char[] carr1 = find(arr1[i]);
			char[] carr2 = find(arr2[i]);

			for (int j = 0; j < N; j++) {
				if (carr1[j] == ' ' && carr2[j] == ' ') {
					answer[i] += ' ';
				} else {
					answer[i] += '#';
				}
			}
		}

		return answer;
	}

	public char[] find(int num) {
		String result = "";

		for (int k = 0; k < N; k++) {
			if (num % 2 == 0) {
				result += ' ';
			} else {
				result += '#';
			}
			num /= 2;
		}
		StringBuffer sb = new StringBuffer(result);
		return sb.reverse().toString().toCharArray();
	}
}
