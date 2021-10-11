package com.baekjoon.problem21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// DFS로 방의 개수와, 그리고 각 방의 시작점을 얻는다.
// BFS로 방 탐색, 현재 내 방의 넓이, 벽을 뚫었을 때 다른 방으로 갈 수 있는지 연결 체크
// 서쪽에 벽이 있을 때는 1을, 북쪽에 벽이 있을 때는 2를, 동쪽에 벽이 있을 때는 4를, 남쪽에 벽이 있을 때는 8을 더한 값이 주어진다. 
public class HN_BOJ2234 {
	static int H, W, roomCnt, maxArea1, maxArea2;
	static int dx[] = { -1, 0, 1, 0 }; // 서 북 동 남
	static int dy[] = { 0, -1, 0, 1 };

	static class Point implements Comparable<Point> {
		int y, x, crush, area;

		public Point(int y, int x, int crush, int area) {
			this.y = y;
			this.x = x;
			this.crush = crush;
			this.area = area;
		}

		public int compareTo(Point p) {
			return Integer.compare(p.area, this.area);
		}
	}

	static int area[];
	static int map[][], fmap[][];
	static Queue<Point> q = new LinkedList<>();
	static boolean visited[][];
	static boolean connect[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		fmap = new int[H][W];
		for (int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < W; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
			}
		}

		// dfs
		visited = new boolean[H][W];
		roomCnt = maxArea1 = maxArea2 = 0;
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (!visited[h][w]) {
					dfs(h, w, roomCnt);
					q.add(new Point(h, w, 0, roomCnt));
					roomCnt++;
				}
			}
		}

		visited = new boolean[H][W];

		connect = new boolean[roomCnt][roomCnt];
		area = new int[roomCnt];
		for (int rc = 0; rc < roomCnt; rc++) {
			Point p = q.poll();
			area[rc] = bfs(p);
			maxArea1 = Math.max(maxArea1, area[rc]);
		}

		for (int i = 0; i < roomCnt; i++) {
			for (int j = 0; j < roomCnt; j++) {
				if (connect[i][j])
					maxArea2 = Math.max(maxArea2, area[i] + area[j]);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(roomCnt).append("\n").append(maxArea1).append("\n").append(maxArea2).append("\n");
		System.out.println(sb);
	}

	private static int bfs(Point p) {
		visited[p.y][p.x] = true;
		Queue<Point> sq = new LinkedList<>();
		sq.add(p);

		int area = 0;
		while (!sq.isEmpty()) {
			int size = sq.size();
			area += size;

			for (int s = 0; s < size; s++) {
				Point sp = sq.poll();

				for (int d = 0; d < dx.length; d++) {
					int ny = sp.y + dy[d];
					int nx = sp.x + dx[d];

					if (check(ny, nx)) {
						boolean isGo = go(sp.y, sp.x, d);

						if (isGo) {
							visited[ny][nx] = true;
							sq.add(new Point(ny, nx, 0, sp.crush));
						} else {
							if (fmap[ny][nx] != p.area)
								connect[p.area][fmap[ny][nx]] = true;
						}
					}
				}
			}
		}

		return area;

	}

	private static void dfs(int y, int x, int a) {
		visited[y][x] = true;
		fmap[y][x] = a;

		for (int d = 0; d < dx.length; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (check(ny, nx)) {
				boolean isGo = go(y, x, d);

				if (isGo) {
					dfs(ny, nx, a);
				}
			}
		}
	}

	private static boolean check(int ny, int nx) {
		if (ny >= H || ny < 0 || nx >= W || nx < 0 || visited[ny][nx])
			return false;

		return true;
	}

	private static boolean go(int y, int x, int d) {
		if (((map[y][x] & (1 << d)) != 0))
			return false;

		return true;
	}

}
