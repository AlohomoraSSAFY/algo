package com.baekjoon.problem39;

import java.util.Arrays;
import java.util.Collections;

// 효율성 테케 1~3 시간초과
// 85점
public class HN_구명보트 {

	public int solution(int[] people, int limit) {
		int answer = 0;
		int len = people.length;
		Integer p[] = new Integer[len];
		for (int i = 0; i < len; i++) {
			p[i] = people[i];
		}
		int boat[] = new int[len];
		boolean full[] = new boolean[len];

		Arrays.sort(p, Collections.reverseOrder());
		int min = p[len - 1];
		int midx = 0;
		for (int i = 0; i < len; i++) {
			for (int j = midx; j < len; j++) {
				if (boat[j] + min > limit) {
					midx++;
					continue;
				}
				if (boat[j] + p[i] > limit || full[j])
					continue;

				if (boat[j] == 0) {
					boat[j] += p[i];
					answer++;
					break;
				} else {
					full[j] = true;
					break;
				}
			}
		}

		return answer;
	}
}
