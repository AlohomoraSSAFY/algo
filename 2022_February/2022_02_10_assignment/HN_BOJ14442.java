package com.baekjoon.problem51;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ14442 {
	static int N, M, K, answer;
	static int map[][];
	static int blockCnt[][];
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static class Point {
		int y, x, bcnt, mcnt;

		public Point(int y, int x, int bcnt, int mcnt) {
			this.y = y;
			this.x = x;
			this.bcnt = bcnt;
			this.mcnt = mcnt;
		}
	}

	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		pq = new PriorityQueue<>((p1, p2) -> {
			if (p1.mcnt == p2.mcnt) {
				return Integer.compare(p1.bcnt, p2.bcnt);
			}
			return Integer.compare(p1.mcnt, p2.mcnt);
		});
		map = new int[N][M];
		blockCnt = new int[N][M];
		for (int r = 0; r < N; r++) {
			char temp[] = br.readLine().toCharArray();
			Arrays.fill(blockCnt[r], Integer.MAX_VALUE);
			for (int c = 0; c < M; c++) {
				map[r][c] = temp[c] - '0';
			}
		}
		answer = -1;
		move();
		System.out.println(answer);
	}

	private static void move() {
		pq.add(new Point(0, 0, 0, 1));
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			int nc = p.mcnt + 1;
			int nb = p.bcnt + 1;

			if (p.y == N - 1 && p.x == M - 1) {
				answer = p.mcnt;
				return;
			}

			for (int i = 0; i < dx.length; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];

				if (bound(ny, nx)) {
					if (map[ny][nx] == 0) {
						// 벽을 적게 부순 경우, 이동횟수와 상관없이 넣는다.
						if (blockCnt[ny][nx] > p.bcnt) {
							blockCnt[ny][nx] = p.bcnt;
							pq.add(new Point(ny, nx, p.bcnt, nc));
						}
					} else {
						// 벽을 부술 수 있는 경우
						if (nb <= K && blockCnt[ny][nx] > nb) {
							blockCnt[ny][nx] = nb;
							pq.add(new Point(ny, nx, nb, nc));
						}
					}
				}
			}
		}
	}

	private static boolean bound(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= N || nx >= M)
			return false;
		return true;
	}
}
