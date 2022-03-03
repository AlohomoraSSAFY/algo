package com.baekjoon.problem52;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//최종정답은 input[temp]보다도 훨씬 작을 수도 있다.

public class HN_BOJ2512 {
	static int N, M, answer;
	static int input[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		int left = 1;
		int right = Arrays.stream(input).max().getAsInt();
		int sum = Arrays.stream(input).sum();
		if (sum < M) {
			answer = right;
		} else {
			int temp, mid = 0;
			while (left <= right) {
				mid = (left + right) >> 1;
				temp = 0;
				for (int i = 0; i < N; i++) {
					if (input[i] > mid) {
						temp += mid;
					} else {
						temp += input[i];
					}
				}

				if (temp > M) {
					right = mid - 1;
				} else {
					left = mid + 1;
					answer = Math.max(answer, mid);
				}
			}
		}
		System.out.println(answer);
	}

}
