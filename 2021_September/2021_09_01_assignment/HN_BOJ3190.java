package com.baekjoon.problem11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ3190_2 {
	static class snape {
		int y, x;

		public snape(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class dir {
		int t;
		int d;

		public dir(int t, int d) {
			this.t = t;
			this.d = d;
		}
	}

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static final int L = -1, D = 1;
	static int N, K, CNT, time;
	static int map[][]; // 사과는 2, 자신의 몸은 1, 빈공간은 0
	static dir[] dlist;
	static List<snape> slength = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ay = Integer.parseInt(st.nextToken());
			int ax = Integer.parseInt(st.nextToken());
			map[ay][ax] = 2;
		}

		CNT = Integer.parseInt(br.readLine());

		dlist = new dir[CNT];
		int t; // 방향과 시간을 담을 배열 생성
		char d;
		for (int i = 0; i < CNT; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			d = st.nextToken().charAt(0);
			if(d == 'L')
				dlist[i] = new dir(t, L);
			else 
				dlist[i] = new dir(t, D);
		}

		int sindex = 0; // 입력받은 방향과 시간에 접근할 인덱스
		int sdir = 0; // 초기 방향
		slength.add(new snape(1,1));
		map[1][1] = 1;
		game: while (true) {
			int idx = slength.size() - 1;
			int nr = slength.get(idx).y + dy[sdir];
			int nc = slength.get(idx).x + dx[sdir];

			// 시간 증가
			time++;
			if (nr == 0 || nc == 0 || nr > N || nc > N || map[nr][nc] == 1) {
				// 게임 종료
				break game;
			}

			// 사과를 먹지 않았으면
			if (map[nr][nc] != 2) {
				// 자신의 뒷꼬리 처리
				snape s = slength.get(0);
				map[s.y][s.x] = 0;
				slength.remove(0);
			} 

			map[nr][nc] = 1;
			slength.add(new snape(nr,nc));

			if (time == dlist[sindex].t) {
				sdir = changeDir(sindex, sdir);
				if (sindex < dlist.length - 1)
					sindex++;
			}
		}

		System.out.println(time);
	}

	private static int changeDir(int index, int dir) {
		// 방향 전환
		
		dir += dlist[index].d;
		// 반시계 방향
		if (dir < 0) {
			dir = dx.length - 1;
		}
		// 시계방향
		if (dir == dx.length) {
			dir = 0;
		}

		return dir;
	}

}
