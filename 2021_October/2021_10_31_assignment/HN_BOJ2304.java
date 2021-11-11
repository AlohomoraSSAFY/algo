package com.baekjoon.problem29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ2304 {
	static int N, LL, RL, ML, answer;
	static int H[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 맨 왼쪽 위치, 맨 오른쪽 위치, 제일 큰 H를 가진 위치
		N = Integer.parseInt(br.readLine());

		H = new int[1001];
		LL = 1001;
		RL = -1;

		int max = Integer.MIN_VALUE;
		int tmpL, tmpH;
		StringTokenizer st;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			tmpL = Integer.parseInt(st.nextToken());
			tmpH = Integer.parseInt(st.nextToken());
			H[tmpL] = tmpH;
			LL = Math.min(LL, tmpL);
			RL = Math.max(RL, tmpL);
			if (max < tmpH) {
				max = tmpH;
				ML = tmpL;
			}
		}

		answer = 0;
		if (ML != LL && ML != RL) {
			moveR(LL, ML);
			moveL(RL, ML);
		} else if (ML == LL) {
			moveL(RL, ML);
		} else if (ML == RL) {
			moveR(LL, ML);
		}
		answer += H[ML];

		System.out.println(answer);
	}

	private static void moveL(int start, int end) {
		int now = H[start];
		for (int i = start; i > end; i--) {
			if (now < H[i]) {
				now = H[i];
			}

			answer += now;
		}
	}

	private static void moveR(int start, int end) {
		int now = H[start];
		for (int i = start; i < end; i++) {
			if (now < H[i]) {
				now = H[i];
			}

			answer += now;
		}

	}

}
