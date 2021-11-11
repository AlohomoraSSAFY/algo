package com.baekjoon.problem30;

import java.util.*;

public class HN_타겟_넘버 {
	static int memo[][];
	static int len, zero;

	public int solution(int[] numbers, int target) {
		len = numbers.length;
		zero = 0;
		memo = new int[len][2001];
		
		// 1 2 3 => 6
		//      4(-2)  5(-1)  6
		for (int l = 0; l < len; l++) {
			Arrays.fill(memo[l], -1);
			zero += numbers[l];
		}

		int answer = dfs(0, 0, target, numbers);
		return answer;
	}

	public static int dfs(int cnt, int sum, int target, int[] numbers) {
		int psum, nsum;

		if (cnt == len) {
			if (sum == target) {
				return 1;
			} else {
				return 0;
			}
		}

		if (memo[cnt][zero + sum] != -1) {
			return memo[cnt][zero + sum];
		}

		memo[cnt][zero + sum] = 0;

		psum = sum + numbers[cnt];
		nsum = sum - numbers[cnt];
		if (psum <= 1000 && psum > -1000) {
			memo[cnt][zero + sum] += dfs(cnt + 1, psum, target, numbers);
		}

		if (nsum <= 1000 && nsum > -1000) {
			memo[cnt][zero + sum] += dfs(cnt + 1, nsum, target, numbers);
		}

		return memo[cnt][zero + sum];
	}
}
