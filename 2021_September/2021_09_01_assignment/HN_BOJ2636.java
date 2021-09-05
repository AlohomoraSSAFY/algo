package com.baekjoon.problem11;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ2636_Cheese {
	static class cheese {
		int y, x;
		boolean remove;

		public cheese(int y, int x, boolean remove) {
			this.y = y;
			this.x = x;
			this.remove = remove;
		}
	}

	static List<cheese> dclist = new LinkedList<>();
	static int R, C, time, cnt, sr, sc;
	static int dx[] = { 0, 0, 1, -1};
	static int dy[] = { 1, -1, 0, 0};
	static int map[][];
	static boolean v[][];

	// 치즈를 기준 탐색이 아닌... 0을 기준으로 탐색해야한다.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		cnt = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int val = Integer.parseInt(st.nextToken());
				map[r][c] = val;
				if (val == 1)
					cnt++;
			}
		}

		while (true) {
			v = new boolean[R][C];
			bfs(0, 0);
			time++;
			// 삭제할 치즈가 없으면 종료
			if (cnt - dclist.size() == 0)
				break;
			delete();
		}
		StringBuilder sb = new StringBuilder();

		sb.append(time).append("\n").append(cnt).append("\n");
		System.out.println(sb);
	}

	private static void delete() {
		cnt -= dclist.size();

		for (int i = dclist.size() - 1; i >= 0; i--) {
			cheese c = dclist.get(i);

			map[c.y][c.x] = 2;
		}

		dclist.clear();
	}

	private static void bfs(int rr, int cc) {

		v[rr][cc] = true;

		for (int idx = 0; idx < dx.length; idx++) {
			int nr = rr + dy[idx];
			int nc = cc + dx[idx];

			if (nr >= 0 && nr < R && nc >= 0 && nc < C && !v[nr][nc]) {
				if (map[nr][nc] == 1) {
					// 치즈 테두리 찾기
					v[nr][nc] = true;
					dclist.add(new cheese(nr, nc, true));
				} else {
					bfs(nr, nc);
				}
			}
		} // for
	}

}
