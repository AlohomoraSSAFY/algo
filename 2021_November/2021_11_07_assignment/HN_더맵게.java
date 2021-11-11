package com.baekjoon.problem31;

import java.util.*;

public class HN_더맵게 {
	static class Scoville implements Comparable<Scoville> {
		int k;

		public Scoville(int k) {
			this.k = k;
		}

		public int compareTo(Scoville s) {
			return Integer.compare(this.k, s.k);
		}
	}

	PriorityQueue<Scoville> pq = new PriorityQueue<>();

	public int solution(int[] scoville, int K) {
		int len = scoville.length;
		for (int l = 0; l < len; l++) {
			pq.add(new Scoville(scoville[l]));
		}

		int answer = 0;
		while (true) {
			Scoville s1 = pq.poll();
			if (s1.k >= K)
				break;

			if (pq.isEmpty()) {
				answer = -1;
				break;
			}

			Scoville s2 = pq.poll();

			int nk = s1.k + s2.k * 2;
			pq.add(new Scoville(nk));
			answer++;
		}

		return answer;
	}

}
