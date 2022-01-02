package com.baekjoon.problem43;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class HN_BOJ2116 {
	static final int SIX = 6;
	static int N;
	static int next[], input[], sum[];
	static Deque<Integer> qs[];
	static boolean check[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		next = new int[SIX + 1];
		check = new boolean[SIX + 1];
		input = new int[SIX];
		sum = new int[SIX];

		qs = new LinkedList[SIX];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < SIX; i++) {
			qs[i] = new LinkedList<>();
			input[i] = Integer.parseInt(st.nextToken());
			qs[i].add(input[i]);
		}
		findNext();
		getSum();

		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < SIX; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			findNext();
			getSum();
		}

		Arrays.sort(sum);
		System.out.println(sum[SIX - 1]);
	}

	private static void getSum() {
		int idx = 0;
		int to = SIX;
		for (int i = 0; i < SIX; i++) {
			to = SIX;
			idx = qs[i].peekLast();
			qs[i].add(next[idx]); 
			check[idx] = check[next[idx]] = true;
			while (check[to]) {
				to--;
			}

			sum[i] += to;
			check[idx] = check[next[idx]] = false;
		}
	}

	private static void findNext() {
		int idx = 0;
		for (int i = 0; i < SIX; i++) {
			idx = input[i];
			if (i == 0 || i == 5) {
				next[idx] = i + 1 == 6 ? input[0] : input[5];
			} else {
				if (i % 2 == 0) {
					next[idx] = i + 2 == 4 ? input[4] : input[2];
				} else {
					next[idx] = i + 2 == 3 ? input[3] : input[1];
				}
			}
		}
	}

}
