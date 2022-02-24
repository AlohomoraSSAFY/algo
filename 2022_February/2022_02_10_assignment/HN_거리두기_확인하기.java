package Kakao;

import java.util.LinkedList;
import java.util.Queue;

public class HN_거리두기_확인하기 {
	static class Point {
		int y, x, d;

		Point(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

	static int answer[];
	static char map[][];
	static Queue<Point> plist;

	public int[] solution(String[][] places) {
		answer = new int[5];
		for (int i = 0; i < 5; i++) {
			map = new char[5][];
			plist = new LinkedList<>();
			for (int j = 0; j < 5; j++) {
				map[j] = places[i][j].toCharArray();
				for (int k = 0; k < 5; k++) {
					if (map[j][k] == 'P') {
						plist.add(new Point(j, k, 0));
					}
				}
			}
			answer[i] = bfs();
		}

		return answer;
	}

	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public int bfs() {
		Queue<Point> q;
		boolean visited[][];
		while (!plist.isEmpty()) {
			visited = new boolean[5][5];
			q = new LinkedList<>();
			Point temp = plist.poll();
			visited[temp.y][temp.x] = true;
			q.add(temp);
			int cnt = 0;
			while (!q.isEmpty()) {
				Point p = q.poll();

				for (int d = 0; d < dx.length; d++) {
					int ny = p.y + dy[d];
					int nx = p.x + dx[d];

					if (bound(ny, nx) && !visited[ny][nx] && map[ny][nx] != 'X') {
						if (map[ny][nx] == 'P') {
							if (p.d <= 1)
								return 0;
							if (++cnt == 4)
								break;
						}

						visited[ny][nx] = true;
						q.add(new Point(ny, nx, p.d + 1));
					}
				}
			}
		}

		return 1;
	}

	public boolean bound(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= 5 || nx >= 5)
			return false;
		return true;
	}
}
