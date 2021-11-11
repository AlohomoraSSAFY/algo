package date1031SUN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2573_2 {
	static int n, m;
	static boolean visited[][];
	static int map[][];
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int answer;
	static ArrayList<int[]> iceberg;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		answer = 0;

		iceberg = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int c = Integer.parseInt(st.nextToken());
				map[i][j] = c;
				if (c != 0)
					iceberg.add(new int[] { i, j, c });
			}
		}

		melt();
		System.out.println(answer);
	}

	public static void melt() {
		while (true) {

			if (zero()) {
				answer = 0;
				return;
			}

			int[][] cnt = new int[n][m];
			answer++;

			for (int i = 0; i < iceberg.size(); i++) { // 동서남북 방향으로 붙어있는 0 개수만큼 감소
				int tx = iceberg.get(i)[0];
				int ty = iceberg.get(i)[1];
				for (int d = 0; d < 4; d++) {
					int nx = tx + dx[d];
					int ny = ty + dy[d];
					if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 0) {
						cnt[tx][ty]++;
					}
				}
			}

			for (int i = 0; i < iceberg.size(); i++) {
				int tx = iceberg.get(i)[0];
				int ty = iceberg.get(i)[1];

				iceberg.get(i)[2] -= cnt[tx][ty];

				if (iceberg.get(i)[2] < 1) {
					map[tx][ty] = 0;
					iceberg.remove(i);
					i--;
				}
			}

			if (seperated())
				return;
		}
	}

	public static boolean seperated() {
		visited = new boolean[n][m];
		int cnt = 0;
		for (int i = 0; i < iceberg.size(); i++) {
			int tx = iceberg.get(i)[0];
			int ty = iceberg.get(i)[1];
			if (!visited[tx][ty]) {
				dfs(tx, ty);
				cnt++;
				if (cnt > 1) {
					return true;
				}
			}
		}
		return false;
	}

	public static void dfs(int x, int y) {
		visited[x][y] = true;

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && map[nx][ny] != 0) {
				visited[nx][ny] = true;
				dfs(nx, ny);
			}
		}
	}

	public static void bfs(int x, int y) {
		visited[x][y] = true;
		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] { x, y });

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int tx = temp[0];
			int ty = temp[1];

			for (int d = 0; d < 4; d++) {
				int nx = tx + dx[d];
				int ny = ty + dy[d];
				if (nx >= 1 && nx < n - 1 && ny >= 1 && ny < m - 1 && !visited[nx][ny] && map[nx][ny] != 0) {
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny });
				}
			}
		}
	}

	public static boolean zero() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] != 0)
					return false;
			}
		}
		return true;
	}
}
