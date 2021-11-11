package com.baekjoon.problem28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ2343_3 {
	static int N, M, answer;
	static int[] input;
	static int MAX, low, high, cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		input = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			int tmp = Integer.parseInt(st.nextToken());
			input[n] = tmp;
			MAX += input[n];
			low = Math.max(low, tmp);
		}

		answer = high = 1_000_000_000;
		while (low <= high) { //
			int mid = (low + high) / 2;

			// m의 개수 구하기
			cnt = 1;
			get(0, mid);
			if (cnt <= M) {
				answer = Math.min(answer, mid);
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		
		System.out.println(answer);
	}

	private static void get(int start, int ref) {
		int sum = 0;
		for (int i = 1; i <= N;) {
			sum += input[i];
			if (sum > ref) {
				cnt++;
				sum = 0;
			} else {
				i++;
			}
		}
	}
}