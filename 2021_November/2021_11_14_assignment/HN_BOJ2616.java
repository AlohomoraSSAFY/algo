package com.baekjoon.problem33;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 9% 시간초과
public class HN_BOJ2616 {
	static int N, M, answer;
	static int input[], subSum[], data[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N + 1];
		subSum = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
			subSum[n] = subSum[n - 1] + input[n];
		}

		M = Integer.parseInt(br.readLine());
		data = new int[N - M + 2];
		for (int i = 1; i <= N - M + 1; i++) {
			data[i] = subSum[i + M - 1] - subSum[i - 1];
		}

		int limit1 = N - (M * 3) + 1;
		int limit2 = N - (M * 2) + 1;
		int limit3 = N - (M * 1) + 1;
		answer = 0;
		for (int f1 = 1; f1 <= limit1; f1++) {
			for (int f2 = f1 + M; f2 <= limit2; f2++) {
				for (int f3 = f2 + M; f3 <= limit3; f3++) {
					answer = Math.max(answer, data[f1] + data[f2] + data[f3]);
				}
			}
		}
		
		System.out.println(answer);
	}

}
