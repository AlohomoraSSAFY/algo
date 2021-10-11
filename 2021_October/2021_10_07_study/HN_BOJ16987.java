package com.baekjoon.problem21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ16987 {
	static int N, answer;
	static int egg[][];
	static boolean broken[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		egg = new int[N][2]; // 0 : 내구도, 1 : 무게
		broken = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			egg[i][0] = Integer.parseInt(st.nextToken());
			egg[i][1] = Integer.parseInt(st.nextToken());
		}

		answer = 0;

		Permu(0, 0);

		System.out.println(answer);
	}

	private static boolean Permu(int now, int num) {
		boolean max = false;

		if (now == N || num == N) {
			answer = Math.max(answer, num);
			if (answer == N)
				max = true;
			return max;
		}

		for (int i = 0; i < N; i++) {
			if (i == now || broken[i]) {
				continue;
			} else if (broken[now]) {
				Permu(now + 1, num);
			} else {
				egg[now][0] -= egg[i][1]; // 내구도에 무게 빼기
				egg[i][0] -= egg[now][1];

				int cnt = 0;
				if (egg[now][0] <= 0) {
					broken[now] = true;
					cnt += 1;
				}
				if (egg[i][0] <= 0) {
					broken[i] = true;
					cnt += 1;
				}

				max = Permu(now + 1, num + cnt);

				if (max)
					return true;

				// 다음 계란을 위해 복구시키기
				egg[now][0] += egg[i][1]; // 내구도에 무게 빼기
				egg[i][0] += egg[now][1];
				broken[now] = broken[i] = false;
			}
		}

		answer = Math.max(answer, num);
		return max;
	}
}
