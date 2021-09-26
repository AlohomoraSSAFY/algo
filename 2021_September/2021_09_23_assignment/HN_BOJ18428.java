package com.baekjoon.problem17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ18428 {
	static int N;
	static int selected[];
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };
	static char map[][];

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static List<Point> Tlist = new LinkedList<>();
	static List<Point> Slist = new LinkedList<>();
	static List<Point> Glist = new LinkedList<>();
	static boolean available, visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		StringTokenizer st;
		for (int h = 0; h < N; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < N; w++) {
				map[h][w] = st.nextToken().charAt(0);
				if (map[h][w] == 'T') {
					Tlist.add(new Point(h, w));
				} else if (map[h][w] == 'S') {
					Slist.add(new Point(h, w));
				}
			}
		}

		available = true;
		createGlist();
		if (available && Glist.size() >= 3) {
			// 장애물 설치 가능, available : 감시를 피할 수 있으면 true
			available = false;

			selected = new int[3];
			Combi(0, 0);
		}
		// 장애물 설치 가능한 장소가 3개 미만인 경우, 무조건 감시를 피할 수 있음
		if (available) {
			// 학생들이 감시를 피할 수 있음
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static void Combi(int cnt, int start) {
		if (available)
			return;

		if (cnt == 3) {
			// 장애물 설치 완료. 선생들이 학생을 발견할 수 있는지 확인
			available = go();
			return;
		}

		for (int i = start; i < Glist.size(); i++) {
			selected[cnt] = i;
			Combi(cnt + 1, i + 1);
		}
	}

	private static boolean go() {
		boolean found = false;
		
		Point G0 = Glist.get(selected[0]);
		Point G1 = Glist.get(selected[1]);
		Point G2 = Glist.get(selected[2]);
		
		check : for (Point tp : Tlist) {
			for (int i = 0; i < dx.length; i++) {
				int ny = tp.y;
				int nx = tp.x;
				while (true) {
					ny += dy[i];
					nx += dx[i];

					if (ny < 0 || ny >= N || nx < 0 || nx >= N)
						break;
					// 설치된 장애물을 만난 경우,
					if(((ny == G0.y)&&(nx == G0.x)) || ((ny == G1.y)&&(nx == G1.x)) || ((ny == G2.y)&&(nx == G2.x))) {
						break;
					}
					
					// 학생을 발견한 경우
					if(map[ny][nx] == 'S') {
						found = true;
						break check;
					}
				}
			}// for
		}// for

		return !found;
	}

	private static void createGlist() {
		boolean visited[][] = new boolean[N][N];
		int src, dest;
		create: for (Point tp : Tlist) {
			for (Point sp : Slist) {
				if ((Math.abs(tp.x - sp.x) == 1 && Math.abs(tp.y - sp.y) == 0)
						|| (Math.abs(tp.x - sp.x) == 0 && Math.abs(tp.y - sp.y) == 1)) {
					// 선생님과 학생이 붙어 있어 장애물 설치 불가능, 무조건 발견됌
					available = false;
					break create;
				} else if (Math.abs(tp.x - sp.x) == 0) {
					// 선생님과 학생이 일직선으로 위치
					src = Math.min(tp.y, sp.y);
					dest = Math.max(tp.y, sp.y);

					for (int i = src + 1; i < dest; i++) {
						if (!visited[i][tp.x] && map[i][tp.x] == 'X') {
							visited[i][tp.x] = true;
							Glist.add(new Point(i, tp.x));
						}
					}
				} else if (Math.abs(tp.y - sp.y) == 0) {
					src = Math.min(tp.x, sp.x);
					dest = Math.max(tp.x, sp.x);

					for (int i = src + 1; i < dest; i++) {
						if (!visited[tp.y][i] && map[tp.y][i] == 'X') {
							visited[tp.y][i] = true;
							Glist.add(new Point(tp.y, i));
						}
					}
				}
			}
		}
	}

}
