package com.baekjoon.problem59;

import java.util.LinkedList;
import java.util.List;

public class HN_1차_다트게임 {
	public int solution(String dartResult) {
		int answer = 0;
		char[] result = dartResult.toCharArray();
		List<Integer> list = new LinkedList<>();

		int point = 0;
		for (int i = 0; i < result.length; i++) {
			int num = result[i] - '0';
			if (num >= 0 && num <= 10) {
				if (result[i] == '1' && result[i + 1] == '0') {
					num = 10;
					i++;
				}
				char bonus = result[++i];
				int mux = 1;
				if (bonus == 'D') {
					mux = 2;
				} else if (bonus == 'T') {
					mux = 3;
				}

				list.add((int) Math.pow(num, mux));
			} else if (result[i] == '*') {
				point = list.size() - 1;
				int end = point - 1 >= 0 ? point - 1 : 0;
				for (int k = point; k >= end; k--) {
					int value = list.get(k) * 2;
					list.set(k, value);
				}
			} else if (result[i] == '#') {
				point = list.size() - 1;

				int value = list.get(point) * -1;
				list.set(point, value);
			}
		}

		for (int s = 0; s < list.size(); s++) {
			answer += list.get(s);
		}

		return answer;
	}

}
