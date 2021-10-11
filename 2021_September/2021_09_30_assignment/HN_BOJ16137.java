package com.baekjoon.problem19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ16137 {
	static int N, M, answer;
	static int[][] map;
	static boolean visited[][][];

	static class Point implements Comparable<Point> {
		int y, x, d;

		public Point(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}

		public int compareTo(Point p) {
			return Integer.compare(this.d, p.d);
		}
	}

	static List<Point>[] bridge = new LinkedList[21];
	static List<Point> clist = new ArrayList<>();
	static int dx[] = { 1, 0, -1, 0 }; // 우, 하, 좌, 상
	static int dy[] = { 0, 1, 0, -1 };
	static int mb = 0, myGoal = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		answer = Integer.MAX_VALUE;
		map = new int[N][N];

		// list 초기화
		for (int i = 0; i < 21; i++) {
			bridge[i] = new LinkedList<>();
		}

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] >= 2) {
					// 현재 만들어진 오작교
					bridge[map[r][c]].add(new Point(r, c, 0));
					mb++;
				}
			} // for
		} // for

		// dfs로 땅에 이름 붙이기
		int num = -1;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1) {
					dfs(r, c, num--);
				}
			} // for
		} // for

		// clist 만들기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 0) {
					addcList(r, c);
				}
			} // for
		} // for

		int repeat = clist.size();
		int idx = bridge[M].size();
		mb = mb + 1;
		visited = new boolean[repeat][N][N];
		for (int i = 0; i < repeat; i++) {
			bridge[M].add(clist.get(i));
			bfs(i);
			bridge[M].remove(idx);
		}

		System.out.println(answer);
	}

	private static void addcList(int r, int c) {
		// 빈공간, 교차 지점인지 아닌지 확인
		int cnt1 = 0; // 좌, 우
		int cnt2 = 0; // 상, 하
		int cnt3 = 0; // 다리가 땅 2개와 인접해야함
		for (int i = 0; i < 4; i++) {
			int ny = r + dy[i];
			int nx = c + dx[i];

			if (check(ny, nx) && map[ny][nx] == 0) {
				if (i % 2 == 0) {
					cnt1++;
				} else {
					cnt2++;
				}
			}
			if (check(ny, nx) && map[ny][nx] < 0) {
				cnt3++;
			}
		}

		if ((cnt1 == 0 && cnt3 >= 2) || (cnt2 == 0 && cnt3 >= 2)) {
			clist.add(new Point(r, c, 0));
		}
	}

	private static void dfs(int r, int c, int status) {
		map[r][c] = status;

		for (int i = 0; i < 4; i++) {
			int ny = r + dy[i];
			int nx = c + dx[i];

			if (check(ny, nx) && map[ny][nx] == 1) {
				dfs(ny, nx, status);
			}
		}

		if (r == N - 1 && c == N - 1) {
			myGoal = status;
		}
	}

	private static void bfs(int k) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0));

		int level = 0;
		int bnum = 0;
		boolean found = false;
		while (!pq.isEmpty()) {
			int size = pq.size();
			level++;

			for (int i = 2; i < 21; i++) {
				if (level % i == 0)
					makeBridge(i, 100);
			}
			boolean tmp[][] = new boolean[N][N];
			for (int s = 0; s < size; s++) {
				Point p = pq.poll();

				// visited를 못쓰므로, 중복으로 Q에 안쌓이게 하기 위함
				if (tmp[p.y][p.x])
					continue;
				tmp[p.y][p.x] = true;

				// 오작교를 다 건넜는데 목적지 땅에 도착하지 못했으면 return
				if (bnum == mb && !found) {
					for (int i = 0; i < 21; i++) {
						makeBridge(i, 0);
					}
					return;
				}
				if (p.y == N - 1 && p.x == N - 1) {
					answer = Math.min(p.d, answer);
					for (int i = 0; i < 21; i++) {
						makeBridge(i, 0);
					}
					return;
				}

				for (int i = 0; i < 4; i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];

					if (check(ny, nx) && !visited[k][ny][nx] && (map[ny][nx] == 100 || map[ny][nx] < 0)) {
						if (map[ny][nx] == 100) { // 오작교만 다시 지나갈 수 없다.
							visited[k][ny][nx] = true;
							bnum++;
							if (check(ny + dy[i], nx + dx[i]) && map[ny + dy[i]][nx + dx[i]] == myGoal)
								found = true;
						}
						pq.add(new Point(ny, nx, level));
					}
				}
			}

			for (int i = 2; i < 21; i++) {
				if (level % i == 0)
					makeBridge(i, 0);
			}
		}
	}

	private static void makeBridge(int time, int status) {
		for (Point p : bridge[time]) {
			map[p.y][p.x] = status;
		}
	}

	private static boolean check(int ny, int nx) {
		if (ny < 0 || ny >= N || nx < 0 || nx >= N)
			return false;
		return true;
	}

}
