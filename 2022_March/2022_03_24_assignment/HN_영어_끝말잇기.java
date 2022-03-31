package com.baekjoon.problem55;

import java.util.Set;
import java.util.TreeSet;

public class HN_영어_끝말잇기 {
	public int[] solution(int n, String[] words) {
		int[] answer = { 0, 0 };

		Set<String> ts = new TreeSet<>();
		boolean pass = true;
		int cnt = 0;
		char end = words[0].charAt(0);
		for (int len = 0; len < words.length; len++) {
			int l = words[len].length() - 1;
			if (ts.contains(words[len]) || words[len].length() <= 1 || end != words[len].charAt(0)) {
				cnt = len;
				pass = false;
				break;
			}
			end = words[len].charAt(l);
			ts.add(words[len]);
		}

		if (!pass) {
			answer[0] = cnt % n + 1;
			answer[1] = cnt / n + 1;
		}

		return answer;
	}

}
