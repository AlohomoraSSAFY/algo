package com.baekjoon.problem21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class HN_BOJ2002 {
	static int N, answer;
	static Queue<String> q1 = new LinkedList<>();
	static Queue<String> q2 = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			q1.add(br.readLine());
		}
		for (int i = 0; i < N; i++) {
			q2.add(br.readLine());
		}

		String q1s = q1.poll();
		String q2s;
		Set<String> del = new HashSet<>();
		for (int i = 0; i < N; i++) {
			q2s = q2.poll();

			if (q1s.equals(q2s)) {
				// 비교해서 맞으면 다음 진입차 검사
				if (i != N - 1) {
					q1s = q1.poll();

					while (true) {
						if (del.contains(q1s)) {
							q1s = q1.poll();
						} else {
							break;
						}
					}
				}
			} else {
				del.add(q2s);
			}
		}

		System.out.println(del.size());
	}
}
