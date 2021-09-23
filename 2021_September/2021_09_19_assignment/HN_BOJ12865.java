package com.baekjoon.problem16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ12865 {
	static int N, K;
	static int bag[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bag = new int[K + 1];
		int W, V;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());

			for (int k = K; k >= 0; k--) {
				if (k - W >= 0)
					bag[k] = Math.max(bag[k], bag[k - W] + V);
				else
					break;
			}
		}

		System.out.println(bag[K]);
	}

}
