package com.baekjoon.problem22;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ2467 {
	static int N, potion[], result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		potion = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			potion[i] = Integer.parseInt(st.nextToken());
		}

		int p1 = 0, p2 = N - 1;
		int answer[] = new int[2];

		result = Integer.MAX_VALUE;
		while (p1 < p2) {
			int tmp = potion[p1] + potion[p2];

			if (result > Math.abs(tmp)) {
				result = Math.abs(tmp);
				answer[0] = p1;
				answer[1] = p2;
			}

			if (tmp >= 0) {
				p2--;
			} else {
				p1++;
			}
		}

		System.out.println(potion[answer[0]] + " " + potion[answer[1]]);
	}

}
