package com.baekjoon.problem49;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class HN_BOJ14226 {
	static final int limit = 1600;
	static int S, answer;
	static boolean visited[][];

	static class Imogi {
		int cnt, clip, second;

		public Imogi(int cnt, int clip, int second) {
			this.cnt = cnt;
			this.clip = clip;
			this.second = second;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		visited = new boolean[limit][limit];
		answer = Integer.MAX_VALUE;

		bfs();

		System.out.println(answer);

	}

	private static void bfs() {
		Queue<Imogi> q = new LinkedList<>();
		visited[0][1] = true;
		q.add(new Imogi(1, 0, 0));

		while (!q.isEmpty()) {
			Imogi i = q.poll();

			if (i.second > answer)
				continue;

			if (i.cnt == S) {
				answer = Math.min(i.second, answer);
				return;
			}

			int temp = i.cnt - 1;
			if (temp > 0 && !visited[i.clip][temp]) {
				visited[i.clip][temp] = true;
				q.add(new Imogi(temp, i.clip, i.second + 1));
			}

			temp = i.cnt + i.clip;
			if (temp < limit && !visited[i.clip][temp]) {
				visited[i.clip][temp] = true;
				q.add(new Imogi(temp, i.clip, i.second + 1));
			}

			if (i.cnt != i.clip) {
				q.add(new Imogi(i.cnt, i.cnt, i.second + 1));
			}
		}
	}
}
