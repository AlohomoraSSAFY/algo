package com.baekjoon.problem40;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HN_BOJ10800 {
	static int N;
	static int[][] balls, SortBalls;
	static int[] sum;
	static Map<Integer, int[]> hm = new HashMap<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		balls = new int[N][3];
		SortBalls = new int[N][3];
		StringTokenizer st;
		int c, s;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			balls[n][0] = SortBalls[n][0] = c;
			balls[n][1] = SortBalls[n][1] = s;
			SortBalls[n][2] = n;
		}

		Arrays.sort(SortBalls, new Comparator<int[]>() {
			public int compare(int[] i1, int[] i2) {
				if (i1[1] == i2[1]) {
					return Integer.compare(i1[0], i2[0]);
				}
				return Integer.compare(i1[1], i2[1]);
			}
		});
		sum = new int[N + 1];
		int[] tempSum = new int[N + 1];
		boolean check[] = new boolean[N + 1];
		int next = 0;
		int idx;
		for (int n = 0; n < N; n = n + next) {
			s = SortBalls[n][1];
			next = 0;
			check[0] = true;
			while (n + next < N && s == SortBalls[n + next][1]) {
				c = SortBalls[n + next][0];
				s = SortBalls[n + next][1];
				idx = SortBalls[n + next][2];
				balls[idx][2] = sum[0] - sum[c];
				next++;
				tempSum[0] += s;
				tempSum[c] += s;
				check[c] = true;
			}
			
			for (int i = 0; i <= N; i++) {
				if (!check[i])
					continue;
				sum[i] += tempSum[i];
				tempSum[i] = 0;
				check[i] = false;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			sb.append(balls[n][2]).append("\n");
		}

		System.out.println(sb);
	}

}
