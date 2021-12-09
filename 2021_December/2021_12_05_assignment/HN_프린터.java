package com.baekjoon.problem37;

import java.util.*;

public class HN_프린터 {
	static class Document {
		int no, priority, wait;

		public Document(int no, int priority, int wait) {
			this.no = no;
			this.priority = priority;
			this.wait = wait;
		}
	}
	
	static Queue<Document> q;
	static int pcnt[];
	public int solution(int[] priorities, int location) {
		q = new LinkedList<>();
		pcnt = new int[10];
		int answer = 0;

		for (int i = 0; i < priorities.length; i++) {
			q.add(new Document(i, priorities[i], 0));
			pcnt[priorities[i]]++;
		}

		while (!q.isEmpty()) {
			Document d = q.poll();

			boolean small = false;
			for (int i = 9; i > d.priority; i--) {
				if (pcnt[i] != 0) {
					small = true;
					break;
				}
			}

			if (small) {
				q.add(d);
			} else {
				pcnt[d.priority]--;
				answer++;
				if (d.no == location) {
					break;
				}
			}
		}

		return answer;
	}
}
