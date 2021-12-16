package com.baekjoon.problem39;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ17837 {
	static int[][] mapInfo;
	static int[][] pInfo;

	static class Point {
		int x, y, dir, no;

		public Point(int x, int y, int dir, int no) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.no = no;
		}
	}

	static Queue<Point> map[][];
	static int N, K, answer;
	static int dx[] = { 0, 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new LinkedList[N + 1][N + 1];
		mapInfo = new int[N + 1][N + 1];
		pInfo = new int[K][3];

		for (int h = 1; h <= N; h++) {
			for (int w = 1; w <= N; w++) {
				map[h][w] = new LinkedList<>();
			}
		}

		for (int h = 1; h <= N; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 1; w <= N; w++) {
				mapInfo[h][w] = Integer.parseInt(st.nextToken());
			}
		}

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < 3; w++) {
				pInfo[k][w] = Integer.parseInt(st.nextToken());
			}
			map[pInfo[k][0]][pInfo[k][1]].add(new Point(pInfo[k][0], pInfo[k][1], pInfo[k][2], k));
		}

		answer = 0;
		play();

		System.out.println(answer);
	}

	private static void play() {
		// 각 라운드당 말을 이동시킨다.
		// pInfo 배열에서 이동시킬 말의 위치를 찾는다.
		// 해당 map의 Q를 순회하면서 말을 찾고 말 위에 다른 말이 있으면 moveQ에 넣는다.
		// 1> 파란색이거나 맵을 벗어나면 pInfo의 이동방향을 반대로 하고 1칸 이동한다.
		// 만약 이동시킬칸도 파란색이거나 맵을 벗어나면 정지시킨다.
		// 2> 이동시키려는 맵이 흰색이면 Queue로 빨간색이면 스택으로 넣는다.
		// map에 붙이고... 해당 위치의 Queue사이즈가 4 넘으면 게임을 종료시킨다.
		int round = 0;

		Deque<Point> moveD = new LinkedList<>();
		while (true) {
			if (++round > 1000) {
				answer = -1;
				return;
			}

			for (int k = 0; k < K; k++) {
				int y = pInfo[k][0];
				int x = pInfo[k][1];
				int dir = pInfo[k][2];

				int size = map[y][x].size();
				boolean add = false;
				boolean finish = false;
				for (int s = 0; s < size; s++) {
					Point p = map[y][x].poll();
					if (p.no == k || add == true) {
						add = true;
						moveD.add(p);
					} else {
						map[y][x].add(p);
					}
				}

				int ny = y + dy[dir];
				int nx = x + dx[dir];
				if (!bound(ny, nx) || mapInfo[ny][nx] == 2) {
					// dir 바꾸기
					if (dir <= 2) {
						dir = pInfo[k][2] = pInfo[k][2] == 2 ? 1 : 2;
					} else {
						dir = pInfo[k][2] = pInfo[k][2] == 3 ? 4 : 3;
					}
					// 새로운 ny, nx 찾기
					ny = y + dy[dir];
					nx = x + dx[dir];

					if (!bound(ny, nx) || mapInfo[ny][nx] == 2) {
						finish = stayPoint(y, x, moveD);
					} else {
						finish = movePoint(ny, nx, moveD);
					}
				} else {
					finish = movePoint(ny, nx, moveD);
				}

				if (finish) {
					answer = round;
					return;
				}
			}
		}
	}

	
	private static boolean stayPoint(int y, int x, Deque<Point> moveD) {
		// 파랑 | 빨강 | 파랑인 경우, 역방향으로 쌓이면 안돼므로 함수를 따로 분리해줘야함.
		int size = moveD.size();
		
		for (int s = 0; s < size; s++) {
			Point p = moveD.pollFirst();
			pInfo[p.no][0] = y;
			pInfo[p.no][1] = x;
			map[y][x].add(p);
		}

		if (map[y][x].size() >= 4) {
			return true;
		}
		return false;
	}

	private static boolean movePoint(int ny, int nx, Deque<Point> moveD) {
		int size = moveD.size();
		if (mapInfo[ny][nx] == 0) {
			for (int s = 0; s < size; s++) {
				Point p = moveD.pollFirst();
				pInfo[p.no][0] = ny;
				pInfo[p.no][1] = nx;
				map[ny][nx].add(p);
			}
		} else {
			for (int s = 0; s < size; s++) {
				Point p = moveD.pollLast();
				pInfo[p.no][0] = ny;
				pInfo[p.no][1] = nx;
				map[ny][nx].add(p);
			}
		}

		if (map[ny][nx].size() >= 4) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean bound(int y, int x) {
		if (y <= 0 || x <= 0 || y > N || x > N)
			return false;
		return true;
	}

}
