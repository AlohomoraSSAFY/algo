package com.baekjoon.problem58;

import java.util.*;

public class HN_올바른_괄호 {

	boolean solution(String s) {
		boolean answer = true;
		char temp[] = s.toCharArray();
		int cnt = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == '(') {
				cnt++;
			} else {
				cnt--;
			}

			if (cnt < 0) {
				answer = false;
				break;
			}
		}

		if (cnt > 0) {
			answer = false;
		}

		return answer;
	}

}
