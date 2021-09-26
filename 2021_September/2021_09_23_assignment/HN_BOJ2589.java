package com.baekjoon.problem17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ2589 {
	static int R, C, answer;
	static char map[][];
	static boolean visited[][];

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static Queue<Point> q = new LinkedList<>();
	static Queue<Point> qt = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}

		int cnt;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if(map[r][c] !='L')	continue;
				cnt = 0;
				for (int t = 0; t < dx.length; t++) {
					int ny = r + dy[t];
					int nx = c + dx[t];

					if (ny < 0 || ny >= R || nx < 0 || nx >= C)
						continue;

					if (map[ny][nx] == 'L')
						cnt++;
				}
				// 육지와 연결이 1개 이상 되어있는 좌표를 얻는다.
				if (cnt != 0) {
					q.add(new Point(r, c));
				}
			}
		} // for

		answer = 0;

		bfs();

		System.out.println(answer);
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			visited = new boolean[R][C];
			qt.add(q.poll());
			visited[qt.peek().y][qt.peek().x] = true;
			int level = -1;
			
			while (!qt.isEmpty()) {
				int size = qt.size();
				level++;
				
				for (int s = 0; s < size; s++) {
					Point cp = qt.poll();
					
					for (int t = 0; t < dx.length; t++) {
						int ny = cp.y + dy[t];
						int nx = cp.x + dx[t];
						
						if (ny < 0 || ny >= R || nx < 0 || nx >= C || map[ny][nx] == 'W' || visited[ny][nx])
							continue;
						
						visited[ny][nx] = true;
						qt.add(new Point(ny, nx));
					}
				}
			}// while
			answer = Math.max(answer, level);
		}//while
	}

}
