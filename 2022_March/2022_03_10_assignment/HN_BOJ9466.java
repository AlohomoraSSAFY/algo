package com.baekjoon.problem53;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1
4
2 3 4 2 
 * */
public class HN_BOJ9466 {
	static final int limit = 100_000;
	static int check[], input[];
	static int T, N, answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		check = new int[limit + 1];
		input = new int[limit + 1];
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				check[n] = 0;
				input[n] = Integer.parseInt(st.nextToken());
				if (n == input[n]) {
					check[n] = n;
				}
			}
			answer = 0;
			for (int n = 1; n <= N; n++) {
				if (check[n] == 0) {
					find(n);
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void find(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		int cur = start;
		int next = input[cur];
		int mid = 0;
		boolean cycle = false;
		while (true) {
			check[cur] = start;
			// cycle 만들기 실패
			if (check[next] == -1)
				break;

			if (check[next] == start) {
				mid = next;
				break;
			}

			if (start == next) {
				// cycle 성공
				cycle = true;
				break;
			}

			q.add(next);
			cur = next;
			next = input[cur];
		}

		if (cycle) {
			while (!q.isEmpty()) {
				check[q.poll()] = start;
			}
		} else {
			int temp;
			while (!q.isEmpty()) {
				temp = q.poll();
				if (mid == temp)
					break;
				check[temp] = -1;
				answer++;
			}
		}
	}

}
