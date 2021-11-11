package com.baekjoon.problem30;

import java.util.*;

public class HN_기능개발 {

	public int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> q = new LinkedList<>();
		int len = progresses.length;
		int sp = 0, ep = 0, end = len;
		while (sp < end) {
			boolean con = true;
			for (int l = sp; l < len; l++) {
				progresses[l] += speeds[l];
				if (con && progresses[l] >= 100) {
					ep++;
				} else {
					con = false;
				}
			}

			if (sp != ep) {
				q.add(ep - sp);
				sp = ep;
			}
		}

		int[] answer = new int[q.size()];
		int idx = 0;
		while (!q.isEmpty()) {
			answer[idx++] = q.poll();
		}
		return answer;
	}

}
