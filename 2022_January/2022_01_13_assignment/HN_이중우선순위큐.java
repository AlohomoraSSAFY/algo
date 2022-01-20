package com.baekjoon.problem47;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_이중우선순위큐 {
	static PriorityQueue<Integer> maxQ, minQ;
	static Map<Integer, Integer> maxM, minM;
	public int[] solution(String[] operations) {
		maxM = new HashMap<>();
		minM = new HashMap<>();
		maxQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		minQ = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2));
		StringTokenizer st;
		int len = operations.length;
		int cnt = 0;
		for (int l = 0; l < len; l++) {
			st = new StringTokenizer(operations[l]);
			char op = st.nextToken().charAt(0);
			int num = Integer.parseInt(st.nextToken());

			if (op == 'I') {
				cnt++;
				maxQ.add(num);
				minQ.add(num);
			} else {
				if (cnt == 0)
					continue;
				cnt--;
				if (num == 1) {
					int temp = maxQ.poll();
					int tcnt = 0;
					if (minM.containsKey(temp)) {
						tcnt = minM.get(temp);
					}
					minM.put(temp, tcnt + 1);
				} else {
					int temp = minQ.poll();
					int tcnt = 0;
					if (maxM.containsKey(temp)) {
						tcnt = minM.get(temp);
					}
					maxM.put(temp, tcnt + 1);
				}
			}
		}

		int[] answer = new int[2];
		if (cnt == 0) {
			return answer;
		}

		while (true) {
			int temp = maxQ.poll();
			int tcnt = 0;
			if (maxM.containsKey(temp)) {
				tcnt = maxM.get(temp);
				if (tcnt == 0) {
					answer[0] = temp;
					break;
				}
				maxM.put(temp, tcnt - 1);
			} else {
				answer[0] = temp;
				break;
			}
		}

		while (true) {
			int temp = minQ.poll();
			int tcnt = 0;
			if (minM.containsKey(temp)) {
				tcnt = minM.get(temp);
				if (tcnt == 0) {
					answer[1] = temp;
					break;
				}
				minM.put(temp, tcnt - 1);
			} else {
				answer[1] = temp;
				break;
			}
		}
		return answer;
	}

}
