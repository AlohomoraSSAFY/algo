package com.baekjoon.problem36;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class HN_BOJ2661 {
	static int N;
	static int[] nums;
	static char[] answer, result;
	static StringBuilder sb1, sb2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		nums = new int[4];
		N = Integer.parseInt(br.readLine());
		answer = new char[N];
		result = new char[N];
		sb1 = new StringBuilder();
		sb2 = new StringBuilder();

		for (int i = 0; i < N; i++) {
			answer[i] = '3';
		}
		
		find(0);

		System.out.println(String.valueOf(answer));
	}

	private static void find(int cnt) {
		if (cnt == N) {
			for (int i = 0; i < answer.length; i++) {
				answer[i] = result[i];
			}
			return;
		}

		for (int i = 1; i <= 3; i++) {
			result[cnt] = (char) ('0' + i);

			if (Character.compare(answer[cnt], result[cnt]) < 0)
				continue;

			if (!check(cnt)) {
				continue;
			}

			find(cnt + 1);
		}
	}

	private static boolean check(int cnt) {
		int gab = (cnt + 1) / 2;

		for (int s = 0; s <= cnt - 1; s++) {
			for (int g = 1; g <= gab; g++) {
				// 3 - 1
				sb1.setLength(0);
				for (int first = s; first < s + g; first++) {
					sb1.append(result[first]);
				}
				if (s + 2 * g > cnt + 1) {
					break;
				}
				sb2.setLength(0);
				for (int second = s + g; second < s + 2 * g; second++) {
					sb2.append(result[second]);
				}

				if (sb1.toString().equals(sb2.toString()))
					return false;
			}
		}

		return true;
	}
}
