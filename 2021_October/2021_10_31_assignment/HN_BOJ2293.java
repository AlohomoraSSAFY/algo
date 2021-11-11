package com.baekjoon.problem29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HN_BOJ2293_1 {
	static int N, K, answer, ncnt;
	static int coin[];
	static int make[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		coin = new int[N];
		make = new int[N][K + 1];
		for (int n = 0; n < N; n++) {
			int tmp = Integer.parseInt(br.readLine());
			coin[n] = tmp;
			make[n][0] = 1;
		}
		
		Arrays.sort(coin);
		
		answer = 0;
		// 전처리
		for (int k = 0; k < K; k++) {
			if (make[0][k] != 0) {
				if (k + coin[0] > K) {
					continue;
				} else if (k + coin[0] == K) {
					answer = 1;
					break;
				}
				make[0][k + coin[0]] = 1;
			}
		}

		for (int n = 1; n < N; n++) {
			for (int k = 0; k < K; k++) {
				if (make[n - 1][k] != 0) {
					for (int m = 0; m < K; m++) { 
						if (k + coin[n] * m > K) {
							break;
						} else if (k + coin[n] * m == K) {
							if (k == 0) {
								answer += 1;
							} else {
								answer += make[n - 1][k];
							}
							break;
						}
						if (k == 0) {
							make[n][coin[n] * m] += 1;
						} else {
							make[n][k + coin[n] * m] += make[n - 1][k];
						}
					}
				}
			}
		}

		System.out.println(answer);
	}

}
