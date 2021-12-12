package com.baekjoon.problem38;

public class HN_입국심사 {
	public long solution(int n, int[] times) {
		long answer = Long.MAX_VALUE;
		long mid;
		long cnt;
		long max = 0;
		for (int i = 0; i < times.length; i++) {
			max = Math.max(max, times[i]);
		}
		long left = 1;
		long right = max * n;
		while (left <= right) {
			mid = (left + right) / 2;
			cnt = 0;

			for (int i = 0; i < times.length; i++) {
				cnt += mid / times[i];
			}

			if (cnt >= n) {
				answer = Math.min(mid, answer);
				right = mid - 1;
			} else {
				left = mid + 1;
			}

		}

		return answer;
	}
}
