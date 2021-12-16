package com.baekjoon.problem39;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ1939 {
	static int N, M, answer;

	static class Bridge {
		int to, value;

		public Bridge(int to, int value) {
			this.to = to;
			this.value = value;
		}
	}

	static List<Bridge> blist[];
	static boolean visited[];
	static int distance[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		blist = new ArrayList[N + 1];
		for (int b = 1; b <= N; b++) {
			blist[b] = new ArrayList<>();
		}

		int f, t, v;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			blist[f].add(new Bridge(t, v));
			blist[t].add(new Bridge(f, v));
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		mst(s, e);

		System.out.println(answer);
	}

	private static void mst(int start, int end) {
		distance = new int[N + 1];
		visited = new boolean[N + 1];
		distance[start] = 1_000_000_001;

		int idx = start, max;
		while (true) {
			max = 0;
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && distance[i] > max) {
					max = distance[i];
					idx = i;
				}
			}

			visited[idx] = true;

			if (idx == end) {
				answer = max;
				return;
			}

			for (Bridge b : blist[idx]) {
				if (!visited[b.to] && distance[b.to] < max && distance[b.to] < b.value ) {
					if(max > b.value) {
						distance[b.to] = b.value;
					}else {
						distance[b.to] = max;
					}
				}
			}
		}
	}

}
