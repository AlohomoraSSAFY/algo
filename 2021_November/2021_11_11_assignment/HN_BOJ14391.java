package com.baekjoon.problem32;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ14391 {
	static int N, M, answer;
	static int input[][];
	static boolean check[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N][M];
		check = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			char tmp[] = br.readLine().toCharArray();
			for (int m = 0; m < M; m++) {
				input[n][m] = tmp[m] - '0';
			}
		}

		answer = 0;
		calculation(check, 0, 0);

		System.out.println(answer);
	}

	private static void calculation(boolean[][] c, int sum, int cnt) {
		boolean copy[][] = copyC(c);
		if (cnt == N * M) {
			answer = Math.max(answer, sum);
			return;
		}
		// 1 => x, y 
		int temp = 0, tm, tn;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (!copy[n][m]) {
					// 오른쪽으로 증가
					copy[n][m] = true;
					temp = input[n][m];
					tm = m + 1;
					while (tm < M && !copy[n][tm]) {
						copy[n][tm] = true;
						temp = temp * 10 + input[n][tm];
						calculation(copy, sum + temp, cnt + tm - m + 1);
						tm++;
					}
					for (int i = 0; i < tm - m; i++) {
						copy[n][m + i] = false;
					}
					
					// 아래쪽으로 증가
					copy[n][m] = true;
					temp = input[n][m];
					tn = n + 1;
					while (tn < N && !copy[tn][m]) {
						copy[tn][m] = true;
						temp = temp * 10 + input[tn][m];
						calculation(copy, sum + temp, cnt + tn - n + 1);
						tn++;
					}
					for (int i = 0; i < tn - n; i++) {
						copy[n + i][m] = false;
					}
					
					copy[n][m] = true;
					calculation(copy, sum + input[n][m], cnt + 1);
				}
			}
		}
	}

	private static boolean[][] copyC(boolean[][] c) {
		boolean copy[][] = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			copy[n] = c[n].clone();
		}
		return copy;
	}

}
