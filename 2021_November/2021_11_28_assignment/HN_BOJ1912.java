package com.baekjoon.problem35;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ1912 {
	static int subSum, answer, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		answer = -100_000_000;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tmp;
		int max = -100_000_000;
		for (int n = 0; n < N; n++) {
			tmp = Integer.parseInt(st.nextToken());
			max = Math.max(max, tmp);

			if (subSum + tmp < 0) {
				subSum = 0;
			} else {
				subSum += tmp;
			}
			answer = Math.max(answer, subSum);
		}

		if (max < 0) // 음수만 입력된 경우
			System.out.println(max);
		else
			System.out.println(answer);
	}

}
