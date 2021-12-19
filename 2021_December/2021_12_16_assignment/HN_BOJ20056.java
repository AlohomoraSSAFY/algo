package com.baekjoon.problem40;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ20056 {
	static int N, M, K, answer;
	static int dx[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

	static class fire {
		int y, x, m, s, dir;

		public fire(int y, int x, int m, int s, int dir) {
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.dir = dir;
		}
	}

	static Queue<fire> q = new LinkedList<>();
	static Queue<fire> map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new LinkedList[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] = new LinkedList<>();
			}
		}
		// 행, 열, 질량, 방향, 속력
		int r, c, m, s, d;
		for (int MM = 0; MM < M; MM++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			q.add(new fire(r, c, m, s, d));
		}

		int k = 0;

		while (k++ < K) {
			move();
		}

		answer = 0;
		while (!q.isEmpty()) {
			answer += q.poll().m;
		}

		System.out.println(answer);
	}

	private static void move() {
		int size = q.size();

		for (int s = 0; s < size; s++) {
			fire f = q.poll();
			int ns = f.s % N;
			int ny = f.y + dy[f.dir] * ns;
			int nx = f.x + dx[f.dir] * ns;

			while (ny > N) {
				ny -= N;
			}
			while (ny < 1) {
				ny += N;
			}
			while (nx > N) {
				nx -= N;
			}
			while (nx < 1) {
				nx += N;
			}

			f.y = ny;
			f.x = nx;

			map[ny][nx].add(f);
		}

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int msize = map[r][c].size();
				if (msize == 0)
					continue;
				if (msize == 1) {
					q.add(map[r][c].poll());
				} else {
					int nm = 0;
					int ns = 0;
					int dir = map[r][c].peek().dir % 2;
					boolean equalDir = true;
					while (!map[r][c].isEmpty()) {
						fire f = map[r][c].poll();
						nm += f.m;
						ns += f.s;
						if (equalDir && dir != (f.dir % 2)) {
							equalDir = false;
						}
					}

					nm /= 5;
					ns /= msize;
					if (nm != 0) {
						int start = 0;
						if (!equalDir) {
							start = 1;
						}
						for (int j = start; j <= 7; j = j + 2) {
							q.add(new fire(r, c, nm, ns, j));
						}
					} // if
				}
			}
		} // for

	}

}
