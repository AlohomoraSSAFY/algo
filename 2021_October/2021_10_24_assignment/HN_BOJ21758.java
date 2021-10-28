package com.baekjoon.problem27;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 55점 통과
public class HN_BOJ21758_2 {
	static int N;
	static int input[];
	static int selected[];
	static long subSum[];
	static long answer, sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N + 1];
		subSum = new long[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		sum = 0;
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			subSum[i] = subSum[i - 1] + input[i];
			if (i != 1 && i != N)
				sum = Math.max(sum, input[i]);
		}

		// 양옆에 벌을 설치하고 중앙에 꿀을 배치한 경우.
		// 수정하기
		answer = (subSum[N] - input[1] - input[N] + sum);

		// 꿀을 맨 오른쪽에 설치한 경우
		selected = new int[2];
		sum = 0;
		Combi1(0, 1);
		answer = Math.max(answer, sum);
		// 꿀을 맨 왼쪽에 설치한 경우
		sum = 0;
		Combi2(0, N);
		answer = Math.max(answer, sum);

		System.out.println(answer);
	}

	private static void Combi2(int cnt, int start) {
		if (start < 1)
			return;

		if (cnt == 2) {
			long tmp = selected[0] < selected[1] ? input[selected[0]] : input[selected[1]];
			long result = (subSum[selected[0]] - input[selected[0]]) + (subSum[selected[1]] - input[selected[1]]) - tmp;
			if (sum < result) {
				sum = result;
			}
			return;
		}

		for (int i = start; i >= 1; i--) {
			selected[cnt] = i;
			Combi2(cnt + 1, i - 1);
		}

	}

	private static void Combi1(int cnt, int start) {
		if (start > N)
			return;

		if (cnt == 2) {
			long tmp = selected[0] > selected[1] ? input[selected[0]] : input[selected[1]];
			long result = (subSum[N] - subSum[selected[0]]) + (subSum[N] - subSum[selected[1]]) - tmp;
			if (sum < result) {
				sum = result;
			}
			return;
		}

		for (int i = start; i <= N; i++) {
			selected[cnt] = i;
			Combi1(cnt + 1, i + 1);
		}

	}

}
