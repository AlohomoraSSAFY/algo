package com.baekjoon.problem23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ13460 {
	static char[][] map;
	static int[] selectedDir;
	static int N, M, answer;
	static int dx[] = { 0, 1, 0, -1 };
	static int dy[] = { 1, 0, -1, 0 };
	static final int max = 10;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static Point R, B, G;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		for (int h = 0; h < N; h++) {
			char tmp[] = br.readLine().toCharArray();
			for (int w = 0; w < M; w++) {
				map[h][w] = tmp[w];
				if (map[h][w] == 'B') {
					B = new Point(h, w);
				} else if (map[h][w] == 'R') {
					R = new Point(h, w);
				} else if (map[h][w] == 'O') {
					G = new Point(h, w);
				}
			}
		}
		
		answer = max + 1;
		selectedDir = new int[max];
		// cnt, old dir
		permu(0, 5);

		System.out.println(answer == max + 1 ? -1 : answer);
	}

	private static void permu(int cnt, int oldDir) {
		if (cnt == max) {
			// 탐색
			int result = go(selectedDir);
			answer = Math.min(answer, result);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (i == oldDir) // 이전에 내가 온 방향과 동일한 방향이면 갈필요 없음
				continue;
			selectedDir[cnt] = i;
			permu(cnt + 1, i);
		}
	}

	private static int go(int[] Dir) {
		char cmap[][] = copyMap(map);
		int cry = R.y;
		int crx = R.x;
		int cby = B.y;
		int cbx = B.x;
		int nry = cry, nrx = crx, nby = cby, nbx = cbx;

		for (int i = 0; i < Dir.length; i++) {
			boolean rmove = true;
			boolean bmove = true;

			boolean rg = false;
			boolean bg = false;
			// R, B 근접여부 체크용
			boolean close = false;
			while (rmove || bmove) {
				if (rmove) {
					nry = cry + dy[Dir[i]];
					nrx = crx + dx[Dir[i]];

					// 1. 범위 체크 && # 아닌지 체크
					if (check(nry, nrx) && cmap[nry][nrx] != '#') {
						// 1-1. 목적지인가?
						if (nry == G.y && nrx == G.x) {
							rg = true;
							rmove = false;
							cmap[cry][crx] = '.';
						} else if (cmap[nry][nrx] == 'B') {
							// 파란색이면 한칸 더 앞을 바라본다.
							int nnry = nry + dy[Dir[i]];
							int nnrx = nrx + dx[Dir[i]];
							if (check(nnry, nnrx) && cmap[nnry][nnrx] != '#') {
								close = true;
							} else {
								// 정지
								rmove = false;
							}
						} else {
							// 이동하고 맵 표시 바꾸기
							cmap[cry][crx] = '.';
							cmap[nry][nrx] = 'R';
							cry = nry;
							crx = nrx;
						}
					} else {
						rmove = false;
					}
				} // rmove

				if (bmove) {
					nby = cby + dy[Dir[i]];
					nbx = cbx + dx[Dir[i]];

					// 1. 범위 체크 && # 아닌지 체크
					if (check(nby, nbx) && cmap[nby][nbx] != '#') {
						// 1-1. 목적지인가?
						if (nby == G.y && nbx == G.x) {
							bg = true;
							bmove = false;
							cmap[cby][cbx] = '.';
						} else if (cmap[nby][nbx] == 'R') {
							// 정지
							bmove = false;
						} else {
							// 이동하고 맵 표시 바꾸기
							cmap[cby][cbx] = '.';
							cmap[nby][nbx] = 'B';
							cby = nby;
							cbx = nbx;
						}
					} else {
						bmove = false;
					}
				} // bmove

				if (close) {
					cmap[cry][crx] = '.';
					cmap[nry][nrx] = 'R';
					close = false;
				}
			}

			if (bg) {
				return max + 1;
			} else if (rg) {
				return i + 1;
			}

		}

		return max + 1;
	}

	private static boolean check(int ry, int rx) {
		// 1. 범위 체크
		if (ry >= 0 && ry < N && rx >= 0 && rx < M)
			return true;

		return false;
	}

	private static char[][] copyMap(char[][] map) {
		char[][] cmap = new char[N][M];

		for (int h = 0; h < N; h++) {
			for (int w = 0; w < M; w++) {
				cmap[h][w] = map[h][w];
			}
		}

		return cmap;
	}

}
