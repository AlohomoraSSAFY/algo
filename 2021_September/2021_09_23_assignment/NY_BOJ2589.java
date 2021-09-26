package date0926SUN;

import java.io.*;
import java.util.*;

class Land {
	int x;
	int y;
	int dist;

	public Land(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class BOJ2589 {

	static int n, m;
	static char map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static int answer;
	static int count;
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					visited = new boolean[n][m];
					bfs(i, j);
					answer = Math.max(answer, count);
				}
			}
		}
		System.out.println(answer);

	}

	public static int bfs(int x, int y) {
		Queue<Land> q = new LinkedList<>();
		visited[x][y] = true;
		q.offer(new Land(x, y, 0));
		count = 0;
		while (!q.isEmpty()) {
			Land l = q.poll();
			count = l.dist;
			for (int d = 0; d < 4; d++) {
				int nx = l.x + dx[d];
				int ny = l.y + dy[d];

				if (nx < n && nx >= 0 && ny < m && ny >= 0 && map[nx][ny] == 'L' && !visited[nx][ny]) {
					visited[nx][ny] = true;

					q.offer(new Land(nx, ny, l.dist + 1));
				}
			}
		}
		return count;
	}

}
