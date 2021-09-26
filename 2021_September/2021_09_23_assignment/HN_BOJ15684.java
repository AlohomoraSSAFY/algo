package com.baekjoon.problem17;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 선이 아예 없거나
// 선이 1개라도 있으면 
public class HN_BOJ15684 {
	static int N, M, H, lsize, answer;
	static boolean[][] ladder, cladder;
	static int lcnt[];

	static class Line {
		int y, x;

		public Line(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int selected[] = new int[3];
	static List<Line> list = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ladder = new boolean[H + 1][N];
		lcnt = new int[N];
		int a = 0, b = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			ladder[a][b] = true;
			lcnt[b]++;
		}

		answer = -1;
		boolean possible = false;
		possible = cp();

		if (M == 0) {
			answer = 0;
		} else if (possible) {
			boolean available = false;

			findLine();
			lsize = list.size();

			for (int i = 0; i <= 3; i++) {
				available = put(i, 0, 0);

				if (available) {
					answer = i;
					break;
				}
			}
		}

		System.out.println(answer);
	}

	private static boolean cp() {
		int cnt = 0;
		for (int i = 1; i < N; i++) {
			if (lcnt[i] % 2 == 1) {
				cnt++;
			}
		}

		if (cnt > 3)
			return false;

		return true;
	}

	private static boolean check(boolean[][] cmap) {
		boolean result = true;

		for (int n = 1; n <= N; n++) {
			int start = n;
			int cn = n;
			for (int h = 1; h <= H; h++) {
				int rl = cn - 1;
				int ll = cn;
				if (rl >= 1 && cmap[h][rl]) {
					cn = cn - 1;
				} else if (ll < N && cmap[h][ll]) {
					cn = cn + 1;
				}
			}

			if (start != cn) {
				result = false;
				break;
			}
		}

		return result;
	}

	private static boolean put(int max, int cnt, int start) {
		boolean result = false;

		if (cnt == max) {
			Line l;
			cladder = copyLadder(ladder);
			for (int i = 0; i < cnt; i++) {
				l = list.get(selected[i]);
				cladder[l.y][l.x] = true;
			}

			result = check(cladder);
			return result;
		}

		for (int i = start; i < lsize; i++) {
			selected[cnt] = i;
			result = put(max, cnt + 1, i + 1);

			if (result)
				return result;
		}

		return result;
	}

	private static boolean[][] copyLadder(boolean[][] ladder) {
		boolean[][] cladder = new boolean[H + 1][N];

		for (int n = 1; n < N; n++) {
			for (int h = 1; h <= H; h++) {
				cladder[h][n] = ladder[h][n];
			}
		}
		return cladder;
	}

	private static void findLine() {
		for (int n = 1; n < N; n++) {
			for (int h = 1; h <= H; h++) {
				// 다리가 이어져 있지 않은 장소를 list에 추가
				if (!ladder[h][n])
					list.add(new Line(h, n));
			}
		}
	}

}
