package com.baekjoon.problem17;

import java.io.*;
import java.util.*;

public class HN_BOJ14501_Success {
	static int N, answer;
	static int Ti[], Pi[], Cost[];
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		Ti = new int[N + 2];
		Pi = new int[N + 2];
		visited = new boolean[N + 1];
		Cost = new int[N + 2];

		StringTokenizer st;
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());

			Ti[n] = Integer.parseInt(st.nextToken());
			Pi[n] = Integer.parseInt(st.nextToken());
		}

		answer = 0;

		go(1, 0);

		System.out.println(answer);
	}

	private static void go(int n, int cost) {
		boolean available = true;

		if (n == N + 1) {
			answer = Math.max(answer, cost);
			return;
		}
		
		if (n + Ti[n] <= N + 1) {
			for (int i = n; i < n + Ti[n]; i++) {
				if (visited[i]) {
					available = false;
					break;
				}
			}
			
			if (available) {
				for (int i = n; i < n + Ti[n]; i++) {
					visited[i] = true;
				}
				go(n + 1, cost + Pi[n]);
				for (int i = n; i < n + Ti[n]; i++) {
					visited[i] = false;
				}
			}
		}
		
		go(n + 1, cost);
	}

}
