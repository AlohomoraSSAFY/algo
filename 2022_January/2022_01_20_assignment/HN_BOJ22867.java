package com.baekjoon.problem48;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ22867 {
	static class Bus implements Comparable<Bus> {
		String start;
		String end;

		public Bus(String start, String end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Bus b) {
			if (this.end.equals(b.end)) {
				return b.start.compareTo(this.start);
			}
			return b.end.compareTo(this.end);
		}
	}

	static int N, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		PriorityQueue<Bus> input = new PriorityQueue<>();
		PriorityQueue<Bus> output = new PriorityQueue<>((s1, s2) -> s2.start.compareTo(s1.start));
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			String start = st.nextToken();
			String end = st.nextToken();
			input.add(new Bus(start, end));
		}

		String curStart = input.peek().start;
		while (!input.isEmpty()) {
			Bus b = input.poll();
			if (b.end.compareTo(curStart) > 0) {
				output.add(b);
				curStart = output.peek().start;
				answer = Math.max(answer, output.size());
			} else {
				output.poll();
				output.add(b);
				curStart = output.peek().start;
			}
		}
		System.out.println(answer);
	}

}
