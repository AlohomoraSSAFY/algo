package date0203;

import java.util.*;

public class NY_카카오프렌즈_컬러링북 {
	static boolean[][] visited;

	public int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int max = 0;

		visited = new boolean[m][n];
		int[] answer = new int[2];

		for (int i = 0; i < picture.length; i++) {
			for (int j = 0; j < picture[0].length; j++) {
				if (picture[i][j] != 0 && !visited[i][j]) {
					numberOfArea++;
					max = Math.max(max, bfs(i, j, picture));
				}
			}
		}
		answer[0] = numberOfArea;
		answer[1] = max;
		return answer;
	}

	public static int bfs(int x, int y, int[][] picture) {
		int sum = 1;
		int dx[] = { 0, 0, 1, -1 };
		int dy[] = { 1, -1, 0, 0 };

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y });
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int temp[] = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				if (nx >= 0 && ny >= 0 && nx < visited.length && ny < visited[0].length && !visited[nx][ny]
						&& picture[nx][ny] == picture[x][y]) {
					sum++;
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny });
				}
			}
		}
		return sum;

	}
}
