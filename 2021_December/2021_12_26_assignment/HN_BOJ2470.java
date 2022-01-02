package com.baekjoon.problem43;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HN_BOJ2470 {
	static int N, P1, P2;
	static int input[], answer[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new int[N];
		answer = new int[2];

		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		}
		P1 = 0;
		P2 = N - 1;
		Arrays.sort(input);
		long min = Integer.MAX_VALUE;
		while (P1 < P2) {
			long temp = input[P1] + input[P2];
			if (min > Math.abs(temp)) {
				min = Math.abs(temp);
				answer[0] = input[P1];
				answer[1] = input[P2];
			}
			if (temp > 0) {
				P2--;
			} else {
				P1++;
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	}

}
