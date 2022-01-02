package com.baekjoon.problem43;

public class HN_H_Index {
	public int solution(int[] citations) {
		int answer = 0;
		int len = citations.length;
		int cnt[] = new int[10001];
		int hRemain[] = new int[10001];

		for (int i = 0; i < len; i++) {
			cnt[citations[i]]++;
		}
 
		hRemain[10000] = len - cnt[10000];
		for (int i = 9999; i >= 0; i--) {
			hRemain[i] = hRemain[i + 1] - cnt[i];
			if (len - hRemain[i] >= i) {
				answer = i;
				break;
			}
		}
		return answer;
	}
}
