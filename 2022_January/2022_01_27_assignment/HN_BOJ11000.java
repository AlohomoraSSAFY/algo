package com.baekjoon.problem49;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ11000 {
	static int N, answer;

	static class Lecture {
		int start, end;

		public Lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		PriorityQueue<Lecture> input = new PriorityQueue<Lecture>((l1, l2) -> {
			if (l2.end != l1.end) {
				return Integer.compare(l2.end, l1.end);
			}
			return Integer.compare(l2.start, l1.start);
		});
		PriorityQueue<Lecture> output = new PriorityQueue<Lecture>((l1, l2) -> Integer.compare(l2.start, l1.start));
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			input.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		answer = 0;
		int curStart = input.peek().start;
		while (!input.isEmpty()) {
			Lecture l = input.poll();
			if (curStart < l.end) {
				output.add(l);
				curStart = output.peek().start;
				answer++;
			} else {
				output.poll();
				output.add(l);
				curStart = output.peek().start;
			}
		}

		System.out.println(answer);
	}

}
