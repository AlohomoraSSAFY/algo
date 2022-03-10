package com.baekjoon.problem52;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HN_빛의_경로_사이클 {
	static int visited[][][];
	static PriorityQueue<Integer> pq;
	static char map[][];
	static int W, H;
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	static class Point {
		int y, x, dir;

		public Point(int y, int x, int dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}

	public int[] solution(String[] grid) {
		H = grid.length;
		W = grid[0].length();
		pq = new PriorityQueue<>((i1, i2) -> {
			return Integer.compare(i1, i2);
		});
		visited = new int[H][W][4];
		map = new char[H][];
		for (int h = 0; h < H; h++) {
			map[h] = grid[h].toCharArray();
		}
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				for (int i = 0; i < 4; i++) {
					if (visited[h][w][i] != 0)
						continue;
					find(h, w, i);
				}
			}
		}
		int[] answer = new int[pq.size()];
		int idx = 0;
		for (Integer i : pq) {
			answer[idx++] = i;
		}
		Arrays.sort(answer);
		return answer;
	}

	public void find(int h, int w, int dir) {
		int cur = 1;
		int oh = h;
		int ow = w;
		int odir = dir;
		visited[h][w][dir] = cur;
		while (true) {
			int nh = oh + dy[odir] >= H ? 0 : oh + dy[odir];
			nh = nh < 0 ? H - 1 : nh;
			int nw = ow + dx[odir] >= W ? 0 : ow + dx[odir];
			nw = nw < 0 ? W - 1 : nw;
			int ndir = odir;
			if (map[nh][nw] == 'L') {
				ndir = ndir - 1 < 0 ? 3 : ndir - 1;
			} else if (map[nh][nw] == 'R') {
				ndir = ndir + 1 >= 4 ? 0 : ndir + 1;
			}

			if (visited[nh][nw][ndir] != 0) {
				pq.add(cur - visited[nh][nw][ndir] + 1);
				// System.out.println(nh+" "+nw+" "+ndir);
				// System.out.println(cur+" : "+visited[nh][nw][ndir]);
				break;
			}
			visited[nh][nw][ndir] = ++cur;
			oh = nh;
			ow = nw;
			odir = ndir;
		}
	}
}
