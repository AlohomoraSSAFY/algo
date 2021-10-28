package com.baekjoon.problem25;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ17142 {
	static int N, M, answer, r, l;
	static int map[][];

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static boolean visited[][];
	static List<Point> selPoint = new LinkedList<>();
	static int nums[];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		// combi
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		// map 복사가 아니라 visited를 쓴다
		int bcnt = 0;
		for (int h = 0; h < N; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < N; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
				if (map[h][w] == 2) {
					selPoint.add(new Point(h, w));
				} else if (map[h][w] == 1) {
					bcnt++;
				}
			}
		} // for
		l = selPoint.size();
		r = (N * N) - l - bcnt;
		answer = Integer.MAX_VALUE;
		nums = new int[M];
		Combi(0, 0, l);

		System.out.println(answer = answer == Integer.MAX_VALUE ? -1 : answer);
	}

	private static void Combi(int cnt, int start, int len) {
		if (cnt == M) {
			Queue<Point> q = new LinkedList<>();
			visited = new boolean[N][N];

			for (int i = 0; i < M; i++) {
				Point p = selPoint.get(nums[i]);
				visited[p.y][p.x] = true;
				q.add(p);
			}
			int time = virous(q, visited, r, answer);

			if (time < answer) {
				answer = time;
			}
			return;
		}

		for (int i = start; i < len; i++) {
			nums[cnt] = i;
			Combi(cnt + 1, i + 1, len);
		}
	}

	private static int virous(Queue<Point> q, boolean[][] v, int remain, int answer) {
		int time = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			if (remain == 0)
				return time;
			
			if (time > answer) {
				return answer;
			}
			
			time++;
			for (int s = 0; s < size; s++) {
				Point p = q.poll();

				for (int i = 0; i < 4; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];

					if (check(ny, nx) && !visited[ny][nx] && map[ny][nx] != 1) {
						if (map[ny][nx] == 0) { // 빈칸
							remain--;
						}
						visited[ny][nx] = true;
						q.add(new Point(ny, nx));
					}
				}
			}

		}

		return answer;
	}

	private static boolean check(int ny, int nx) {
		if (ny >= 0 && ny < N && nx >= 0 && nx < N)
			return true;
		return false;
	}

}
