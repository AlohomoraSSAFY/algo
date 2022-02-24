package date0224;

import java.util.*;

public class NY_거리두기_확인하기 {
	public int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		Arrays.fill(answer, 1);
		for (int tc = 0; tc < places.length; tc++) {
			boolean flag = false;
			for (int i = 0; i < places[0].length; i++) {
				if (flag)
					break;
				for (int j = 0; j < places[0][0].length(); j++) {
					if (places[tc][i].charAt(j) == 'P') {
						if (!bfs(places[tc], i, j)) {
							answer[tc] = 0;
							flag = true;
							break;
						}
					}
				}
			}

		}
		return answer;
	}

	public boolean bfs(String[] places, int xx, int yy) {
		int r = places.length;
		int c = places[0].length();

		int dx[] = { 0, 1, -1, 0 };
		int dy[] = { 1, 0, 0, -1 };

		boolean[][] visited = new boolean[r][c];

		Queue<int[]> q = new LinkedList<>();

		visited[xx][yy] = true;
		q.add(new int[] { xx, yy, 0 });

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			int dist = temp[2];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < r && ny < c && nx >= 0 && ny >= 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					char ch = places[nx].charAt(ny);
					if (ch == 'P') {
						if (dist < 2)
							return false;
					} else if (ch == 'O' && dist < 2) {
						q.offer(new int[] { nx, ny, dist + 1 });
					}

				}

			}
		}

		return true;
	}
}
